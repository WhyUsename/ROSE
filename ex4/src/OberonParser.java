/**
 * author Zemin Chen
 * update 10-07-02
 * parsing the oberon program
 */
import flowchart.*;
import exceptions.*;
import java.util.*;

/**
 * @author Zemin Chen
 * in the parsing process,it can find kinds or error
 * cannot error recovery
 */
public class OberonParser 
{
	
	public Stack<StatementSequence> sign;
	
	public Vector<Type>	caller;
	public Vector<Type>	callee;
	public Vector<Type> type_v;
	public OberonScanner scan;
	public Symbol lookahead;
	int LeftParen;
	int RightParen;
	
	Module testModule;              // instance of a module
	Procedure proc;                 // instance of a procedure
	WhileStatement whileStmt;       // instance of a WHILE statement
	IfStatement ifStmt;             // instance of a IF statement

	/**
	 * @param scan
	 * @throws Exception
	 * initial the varibles in the OberonParser class
	 */
	public OberonParser(OberonScanner scan) throws Exception
	{
		sign = new Stack<StatementSequence>();
		
		LeftParen = 0;
		RightParen = 0;
		callee = new Vector<Type>();
		type_v = new Vector<Type>();
		caller = new Vector<Type>();
		
		testModule = null;
		proc = null;	
		whileStmt = null;
		ifStmt = null;
		
		this.scan = scan;

	}
	
	/**
	 * @return
	 * @throws Exception
	 * to begin the module program analyze
	 */
	public boolean module()throws Exception
	{
		String name1, name2;
		
		lookahead = get_token();	//get MODULE
		lookahead = get_token();	//get module name1
		testModule = new Module(lookahead.name);
		
		
		System.out.println("The module is " + lookahead.name);
		name1 = lookahead.name;
		
		lookahead = get_token();	//get SEMI
		
		lookahead = get_token();
		//if (lookahead.sym != Token.END && lookahead.sym != Token.BEGIN)
			declaration();
		if (lookahead.sym == Token.BEGIN)
			module_begin();
		
		lookahead = get_token();	//get module name2
		name2 = lookahead.name;
		if (name1.equals(name2) == false)
			throw new ModuleNameMismatchedException();
		lookahead = get_token();	//get PERIOD
		
		//check whether the Lparen and Rparen is matched
		if (LeftParen < RightParen)
			throw new MissingLeftParenthesisException();
		else if (LeftParen > RightParen)
			throw new MissingRightParenthesisException();
		//check the callee is valid
		int name_match = 0;
		for (int i=0; i<callee.size(); i++)
		{
			for (int j=0; j<caller.size(); j++)
			{
				if (callee.elementAt(i).name.equals(caller.elementAt(j).name))
				{
					if (callee.elementAt(i).id != caller.elementAt(j).id)	//the num of the para
						throw new MissingOperandException();
					//if (callee.elementAt(i).recordElement.equals(caller.elementAt(j).recordElement) == false)	//the type of para 

					for (int k=0; k<callee.elementAt(i).id; k++)
						if (callee.elementAt(i).recordElement.elementAt(k).id !=
							caller.elementAt(j).recordElement.elementAt(k).id)
							throw new TypeMismatchedException();
					name_match = 1;
				}
			}
			if (name_match == 0)
				throw new SemanticException(" "+callee.elementAt(i).name+" is not declared");
			name_match = 0;
		}
		System.out.println("********* Succeed in paring ! **********");
		testModule.show();
		return true;
	}

	/**
	 * the declaration of the module or the proceduer
	 * CONST part
	 * TYPE part
	 * VAR part
	 * PROCEDURE part
	 * declaration part (recur)
	 * @throws Exception
	 */
	public void declaration() throws Exception
	{
		//lookahead = get_token();
		if (lookahead.sym == Token.CONST)
			const_declare();
		else if (lookahead.sym == Token.TYPE)
			type_declare();
		else if (lookahead.sym == Token.VAR)
			var_declare();
		else if (lookahead.sym == Token.PROCEDURE)
			procedure_declaration();

		if (lookahead.sym == Token.BEGIN || lookahead.sym == Token.END)
			return;
		else
			declaration();
	}
	
	/**
	 * including the heading or the body of the procedure.
	 * no para
	 * @throws Exception
	 */
	public void procedure_declaration() throws Exception
	{
		String name1, name2;
		name1 = procedure_heading();
		
		lookahead = get_token();
		name2 = procedure_body();
		
		//System.out.println(name2 +" "+ name1);
		if (name1.equals(name2) == false)
			throw new ProcedureNameMismatchedException();
		
		lookahead = get_token();	//get SEMI after procedure name
		lookahead = get_token();
		//if (lookahead.sym == Token.PROCEDURE)
		//	procedure_declaration();
	}
	
	/**
	 * parsing the procedure body
	 * @return the procedure name
	 * @throws Exception
	 */
	public String procedure_body() throws Exception
	{
		declaration();
		//lookahead = get_token();
		if (lookahead.sym == Token.BEGIN)
			procedure_begin();
		
		//sign.pop();					//pop the proc out of the stack
		lookahead = get_token();	//get procedure name
		return lookahead.name;
	}
	
	/**
	 * parsing the procedure statement when see "BEGIN"
	 * @throws Exception
	 */
	public void procedure_begin() throws Exception
	{
		statement();
		
	}
	
	/**
	 * parsing the heading or the procedure
	 * @return the proceduer name
	 * @throws Exception
	 */
	public String procedure_heading() throws Exception
	{
		Type fp;
		String name;
		lookahead = get_token();	//get procedure name
		name = new String (lookahead.name);
		lookahead = get_token();	//get LPAREN OR SEMI
		if (lookahead.sym == Token.LPAREN)
		{
			fp = formal_parameters(name);
			lookahead = get_token();	//get SEMI
		}
		else
			fp = new Type();
		fp.name = name;
		caller.addElement(fp);
		
		proc = testModule.add(name);
		//sign.push(proc);	//push the proc into stack
		
		return name;
	}
	
	/**
	 * parsing the para of the procedure.
	 * store the infor of the para of the procedure.
	 * @param name
	 * @return the para infor of the procedure.
	 * @throws Exception
	 */
	public Type formal_parameters(String name) throws Exception
	{
		Type fp = new Type(0, name, new Vector<Type>());
		lookahead = get_token();
		if (lookahead.sym == Token.RPAREN)
			return fp;
		fp_section(fp);		//at least one para
	
		return fp;
	}
	
	
	/**
	 * the current lookahead is the ID.
	 * at least one para will be counted.
	 * @param fp.
	 * @throws Exception.
	 */
	public void fp_section(Type fp) throws Exception
	{
		Type fp_t;
		Vector<String> fp_name = new Vector<String>();
		if (lookahead.sym == Token.VAR)
			lookahead = get_token();
		id_list(fp_name, lookahead);
		fp_t =  type_id();
		
		for (int i=0; i<fp_name.size(); i++)
		{
			//		//the para just get the type, not the para name
			fp.recordElement.addElement(new Type(fp_t));
			
			fp_t.name = fp_name.elementAt(i);
			type_v.addElement(new Type(fp_t));
		}
		fp.id += fp_name.size();
		
		lookahead = get_token();
		if (lookahead.sym == Token.SEMI)
		{
			lookahead = get_token();
			fp_section(fp);
		}
	}
	
	/**
	 * parsing the CONST part
	 * @throws Exception
	 */
	public void const_declare()throws Exception
	{
		Type temp = new Type();
		Symbol t;
		
		lookahead = get_token();
		if (lookahead.sym != Token.IDENTIFIER)
			return ;
		temp.name = lookahead.name;
		
		lookahead = get_token();	//get EQ
		t = expression();
		temp.id = t.sym;		
		type_v.addElement(temp);
		
		const_declare();
	}
	
	/**
	 * parsing the VAR part
	 * @throws Exception
	 */
	public void var_declare() throws Exception
	{
		Type temp;
		Vector<String> id_v = new Vector<String>();
		if (lookahead.sym == Token.VAR)
			lookahead = get_token();
		id_list(id_v, lookahead);
		
		//lookahead = get_token();	//get colon
		temp = type_id();
		for (int i=0; i<id_v.size(); i++)
		{
			temp.name = id_v.elementAt(i);
			type_v.addElement(new Type(temp));
		}
		
		lookahead = get_token();	//get SEMI
		lookahead = get_token();
		if (lookahead.sym == Token.IDENTIFIER)
			var_declare();
		else	return;
	}
	
	/**
	 * parsing the TYPE part
	 * @throws Exception
	 */
	public void type_declare()throws Exception
	{
		Type temp;
		
		lookahead = get_token();
		if (lookahead.sym != Token.IDENTIFIER)
			return ;
		String name = new String(lookahead.name);
		
		lookahead = get_token();	//get EQ
		temp = type_id();
		temp.name = name;
		type_v.addElement(temp);
		lookahead = get_token();	//get SEMI
		
		type_declare();
	}
	
	/**
	 * to get which type it is.
	 * @return the Type of the element.
	 * maybe INTEGER,BOOLEAN,RECORD,ARRAY.
	 * @throws Exception
	 */
	public Type type_id() throws Exception
	{
		
		lookahead = get_token();
		if (lookahead.sym == Token.INTEGER)
			return new Type(1);		//int
		else if (lookahead.sym == Token.BOOLEAN)
			return new Type(2);		//bool
		else if (lookahead.sym == Token.IDENTIFIER)
		{
			for (int i=0; i<type_v.size(); i++)
			{
				if (lookahead.name.equals(type_v.elementAt(i).name))
					return type_v.elementAt(i);
			}
			throw new SemanticException("varible( "+ lookahead.name +" ) without declaration!");
		}
		else if (lookahead.sym == Token.ARRAY)
			return array_type();
		else if (lookahead.sym == Token.RECORD)
			return record_type();
		else
			throw new SyntacticException();
	}
	
	/**
	 * parsing array type, know what element of the array.
	 * @return ARRAY Type
	 * @throws Exception
	 */
	public Type array_type() throws Exception
	{
		Type arr = new Type(3);
		
		Symbol temp = expression();
		if (temp.sym != 1)
			throw new TypeMismatchedException();
		
		arr.arrayElement = new Type(type_id());
		return arr;
	}

	/**
	 * parsing RECORD Type, know what element in the record.
	 * @return RECORD Type
	 * @throws Exception
	 */
	public Type record_type() throws Exception
	{
		Type temp = new Type(4);
		temp.recordElement = new Vector<Type>();
		lookahead = get_token();
		if (lookahead.sym == Token.END)
			return temp;
		return field_list(temp);
	}

	/**
	 * parsing the field part
	 * @param inh
	 * @return the element infor of the record.
	 * @throws Exception
	 */
	public Type field_list(Type inh) throws Exception
	{
		Vector<String> temp = new Vector<String>();
		id_list(temp, lookahead);
		
		if (lookahead.sym == Token.END)
			return inh;
		else if (lookahead.sym == Token.SEMI)	//just a SEMI in the field
		{
			lookahead = get_token();
			return field_list(inh);
		}
		
		//lookahead = get_token();	//get colon
		if (temp.isEmpty() == false)
		{
			Type t = type_id();
			//inh.recordElement = new Vector<Type>();
			for (int i=0; i<temp.size(); i++)
			{
				t.name = temp.elementAt(i);
				inh.recordElement.addElement(new Type(t));
			}
		}
		
		lookahead = get_token();	//get SEMI or END
		return field_list(inh);
		/*
		if (lookahead.sym == Token.SEMI)
			return field_list(inh);
		else if (lookahead.sym == Token.END)
			return inh;
		else
			throw new SyntacticException();		
		*/
	}

	/**
	 * pasing the IDENTIFIER seperated by comma
	 * @param id_v
	 * @param id
	 * @throws Exception
	 */
	public void id_list(Vector<String> id_v, Symbol id) throws Exception
	{
		if (id.sym != Token.IDENTIFIER)
			return;
		id_v.addElement(id.name);
		
		lookahead = get_token();
		if (lookahead.sym != Token.COMMA)
			return ;
		//lookahead = get_token();
		else
		{
			lookahead = get_token();
			id_list(id_v, lookahead);
		}
	}
	
	/**
	 * parsing the statment in the module.
	 * @throws Exception
	 */
	public void module_begin() throws Exception
	{
		proc = testModule.add("Main");
		statement();	
	}

	
	/**
	 * parsing the statement.
	 * @throws Exception
	 */
	public void statement() throws Exception
	{
		Type ap_type;
		String name;
		String ap = new String();
		if (lookahead.sym == Token.END)
			return;
		lookahead = get_token();
		
		if (lookahead.sym == Token.WHILE)
			while_statement();
		else if (lookahead.sym == Token.IF)
			if_statement();
		else if (lookahead.sym == Token.READ || 
				lookahead.sym == Token.WRITE || lookahead.sym == Token.WRITELN)
			rw_statement(lookahead.sym);
		else if (lookahead.sym == Token.IDENTIFIER)
		{
			name = new String (lookahead.name);
			lookahead = get_token();
			if (lookahead.sym == Token.LPAREN || lookahead.sym == Token.SEMI)	//callee
			{
				ap_type = new Type(0, name, new Vector<Type>());
				if (lookahead.sym != Token.SEMI)
				{
					//lookahead = get_token();
					ap = ap_list(ap_type);
					//System.out.println(ap);
				}
				callee.addElement(ap_type);
				
				String t = new String(name+"( "+ap+" )");
				add_stmt(new PrimitiveStatement(t));	//add procedure call****************
			}
			else
				assignment(name);
		}
		else if (lookahead.sym == Token.SEMI)
			;
		
		if (lookahead.sym == Token.ELSE || lookahead.sym == Token.ELSIF)
			return ;
		statement();	//empty statement or after statement
			
		//sign = Token.PROCEDURE;
		
	}

	
	/**
	 * parsing the assign statement.
	 * @param name
	 * @throws Exception
	 */
	public void assignment(String name) throws Exception
	{
		Symbol left, right;
		Type id_t;
		
		left = new Symbol(0, new String());
		for (int i=0; i<type_v.size(); i++)
		{
			if (type_v.elementAt(i).name.equals(name))
			{
				id_t = type_v.elementAt(i);
				selector(id_t, left);
				break;
			}
		}
		if (left.sym == 0)
			throw new SemanticException("varible( "+ name +" ) without declaration!");
		else
		{
			//get_token();	//get ASSIGN
			right = expression();
			if (left.sym != right.sym)
				throw new TypeMismatchedException();
			add_stmt(new PrimitiveStatement(name+" := "+right.name));		//add assign statement**************
		}	
	}

	/**
	 * parsing the para of the procedure call as a statement.
	 * @param ap_type
	 * @return the para infor of the procedure call.
	 * @throws Exception
	 */
	public String ap_list(Type ap_type) throws Exception
	{
		Symbol expr;
		expr = expression();
		ap_type.recordElement.addElement(new Type(expr.sym));
		ap_type.id++;	//the number of the ap
		
		if (lookahead.sym == Token.RPAREN)
			return expr.name;
		
		if (lookahead.sym != Token.RPAREN)
		{
			expr.name += ", ";
			expr.name += ap_list(ap_type);
		}
		return expr.name;
	}
	
	
	/**
	 * write or read or writeln statement.
	 * @param sym
	 * @throws Exception
	 */
	public void rw_statement(int sym) throws Exception
	{
		String t;
		Symbol expr = new Symbol(0);
		if (sym == Token.WRITELN)
		{
			lookahead = get_token();		//may get SEMI
			if (lookahead.sym == Token.LPAREN)
			{
				expr = expression();		//lookahead is RPAREN
				if (lookahead.sym != Token.RPAREN)
					throw new MissingRightParenthesisException();
				lookahead = get_token();	//get SEMI or END of procedure
				t = new String("Write("+expr.name+")");
			}
			else
				t = new String("Writeln");
		}
		else if (sym == Token.READ)
		{
			lookahead = get_token();	//get LPAREN
			if (lookahead.sym != Token.LPAREN)
				throw new MissingLeftParenthesisException();
			
			expr = expression();
			//lookahead = get_token();	//get ID in the READ
			if (expr.sym == 0)
				throw new MissingOperatorException();
			
			//lookahead = get_token();	//get RPAREN
			if (lookahead.sym != Token.RPAREN)
				throw new MissingRightParenthesisException();
			lookahead = get_token();		//may get SEMI of END of procedure
			t = new String("Read( "+expr.name+" )");
		}
		else	//WRITE
		{
			lookahead = get_token();	//get LPAREN
			if (lookahead.sym != Token.LPAREN)
				throw new MissingLeftParenthesisException();
			
			expr = expression();	//lookahead is RPAREN
			if (expr.sym == 0)
				throw new MissingOperatorException();
			//check the expr.sym == 0
			if (lookahead.sym != Token.RPAREN)
				throw new MissingRightParenthesisException();
				
			t = new String("Write( "+expr.name+" )");
			lookahead = get_token();
		}
		add_stmt(new PrimitiveStatement(t));		//add rw-statement******************
	}

	/**
	 * parsing while statement.
	 * @throws Exception
	 */
	public void while_statement() throws Exception
	{
		Symbol expr;
		
		expr = expression();	//the lookahead must be DO
		
		whileStmt = new WhileStatement(expr.name);
		add_stmt(whileStmt);
		
		sign.push(whileStmt.getLoopBody());	//push the whileStmt into the stack
		statement();			//the lookahead must be END of while_statement
		sign.pop();				//pop it whileLoopBody
		lookahead = get_token();
	}

	
	/**
	 * parsing the if statement
	 * @throws Exception
	 */
	public void if_statement() throws Exception
	{
		Symbol expr;
		
		expr = expression();
		
		ifStmt = new IfStatement(expr.name);
		add_stmt(ifStmt);

		sign.push(ifStmt.getFalseBody());
		sign.push(ifStmt.getTrueBody());
		
		statement();
		sign.pop();		//pop the ifTureBody**********
		
		if (lookahead.sym == Token.ELSIF)
		{
			elsif_statement();
		}
		if (lookahead.sym == Token.ELSE)
		{
			else_statement();
		}
		
		if (lookahead.sym == Token.END)	//if_statement is over
			lookahead = get_token();

		sign.pop();		//pop the IfFalse body******************
	}
	
	
	/**
	 * parsing the elsif statement
	 * @throws Exception
	 */
	public void elsif_statement() throws Exception
	{
		Symbol expr;
		expr = expression();	//the lookahead is THEN
		
		IfStatement elsif = new IfStatement(expr.name);
		add_stmt(elsif);	//ifFalseBody add elsif
		sign.pop();			//pop the ifFalseBody
		
		sign.push(elsif.getFalseBody());
		sign.push(elsif.getTrueBody());	//push
		
		statement();
		sign.pop();			//pop the elsifTrueBody
		
		System.out.println(lookahead.name+"123");
		if (lookahead.sym == Token.ELSIF)
			elsif_statement();
	}
	
	
	/**
	 * parsing the else statement
	 * @throws Exception
	 */
	public void else_statement() throws Exception
	{	
		statement();
		//the lookahead now is END of if_statement
	}

	/**
	 * parse the expression.
	 * @return Symbol whick include the content of the expression and the type of the expression
	 * @throws Exception
	 */
	public Symbol expression() throws Exception
	{
		Symbol expr = new Symbol(2);	//return boolean
		expr = simple_expression();
		
		int t = lookahead.sym;
		if (t==Token.EQ || t==Token.NEQ || t==Token.LT ||
				t==Token.LE || t==Token.GT || t==Token.GE )
		{
			if (t == Token.EQ)	expr.name += " = ";
			if (t == Token.NEQ) expr.name += " # ";
			if (t == Token.LT)	expr.name += " &lt ";
			if (t == Token.LE)	expr.name += " &lt = ";
			if (t == Token.GT)	expr.name += " &gt ";
			if (t == Token.GE)	expr.name += " &gt = ";
			expr.name = expr.name.toString() + simple_expression().name.toString();
			expr.sym = 2;
		}
		return expr;
	}

	
	/**
	 * parse the simple expression.
	 * the operator are "+", "-", "OR".
	 * @return Symbol whick include the content of the expression and the type of the expression
	 * @throws Exception
	 */
	public Symbol simple_expression() throws Exception
	{
		Symbol expr = new Symbol(2);
		expr = term();
		int t = lookahead.sym;
		if (t==Token.PLUS || t==Token.MINUS || t==Token.OR)
		{
			if (t == Token.PLUS)	expr.name += "+";
			if (t == Token.MINUS)	expr.name += "-";
			if (t == Token.OR)
			{
				expr.sym = 2;
				expr.name += "OR";
			}
			expr.name += simple_expression().name;
		}
		return expr;
	}

	
	/**
	 * parse the term item.
	 * the operator are "*", "DIV", "MOD", "&".
	 * @return Symbol whick include the content of the expression and the type of the expression
	 * @throws Exception
	 */
	public Symbol term() throws Exception
	{
		Symbol expr = new Symbol(2);
		expr = factor();
		int t = lookahead.sym;
		if (t==Token.TIMES || t==Token.DIVIDE || t==Token.MOD || t==Token.AND)
		{
			if (expr.sym != 2 && t==Token.AND)
				throw new TypeMismatchedException();
			if (expr.sym != 1 && t!=Token.AND)
				throw new TypeMismatchedException();
			if (t == Token.TIMES)	expr.name += " * ";
			if (t == Token.DIVIDE)	expr.name += " DIV ";
			if (t == Token.MOD)		expr.name += " MOD ";
			
			if (t == Token.AND)		
			{
				expr.name += " & ";
				expr.sym = 2;
			}
			expr.name += term().name;
		}
		else if(t==Token.IDENTIFIER || t==Token.NUMBER || t==Token.BOOLEAN)
			throw new MissingOperatorException();
		return expr;
	}
	
	
	/**
	 * parsing a factor
	 * @return Symbol, as a factor
	 * @throws Exception
	 */
	public Symbol factor() throws Exception
	{
		Symbol s = new Symbol(0, new String());
		
		lookahead = get_token();
		int sym = lookahead.sym;
		if (sym == Token.NUMBER)
		{
			s = new Symbol(1, lookahead.name);
			lookahead = get_token();
		}
		else if (sym == Token.NOT)
		{
			s = factor();
			lookahead = get_token();
		}
		else if (sym == Token.IDENTIFIER)
		{
			int i;
			for (i=0; i<type_v.size(); i++)
			{
				if (lookahead.name.equals(type_v.elementAt(i).name))
				{
					selector(type_v.elementAt(i), s);
					break;
				}
			}
			if (i >= type_v.size())
				throw new SemanticException("varible( "+ lookahead.name +" ) without declaration!");
			if (s.sym == 0)
			{
				s = new Symbol(type_v.elementAt(i).id, type_v.elementAt(i).name);
				lookahead = get_token();
			}
			
		}
		else if (sym == Token.LPAREN)
		{
			s = expression();			//the RPAREN has been scanned
			if (lookahead.sym != Token.RPAREN)
				throw new MissingRightParenthesisException();
			s.name = "( " + s.name + " )";
			lookahead = get_token();	//get the token after RPAREN
		}
		else if (sym==Token.PLUS || sym==Token.MINUS || sym==Token.TIMES || sym==Token.DIVIDE ||
				sym==Token.AND || sym==Token.OR || sym==Token.NOT || sym==Token.LT || 
				sym==Token.LE ||sym==Token.GT || sym==Token.GE || sym==Token.NEQ || sym==Token.EQ)
			throw new MissingOperandException();
		
		if (s.sym == 0) throw new MissingOperandException();
		return s;
	}
	
	
	/**
	 * parsing the selector part, it maybe nothing.
	 * @param t
	 * @param s
	 * @throws Exception
	 */
	public void selector(Type t, Symbol s) throws Exception
	{
		Symbol expr;
		if (t.name != null)
			s.name = s.name + t.name;
		if (lookahead.sym == Token.IDENTIFIER)
			lookahead = get_token();
		if (lookahead.sym == Token.PERIOD)
		{
			int match = 0;
			lookahead = get_token();
			for (int i=0; i<t.recordElement.size(); i++)
			{
				t = t.recordElement.elementAt(i);
				if (t.name.equals(lookahead.name))
				{
					match = 1;
					s.name = s.name + ("."+lookahead.name);
					selector(t, s);
				}
			}
			if (match == 0)
				throw new SemanticException("varible( "+ lookahead.name +" ) in RECORD"+ t.name + "without declaration!");
		}
		else if (lookahead.sym == Token.LBRACKET)
		{
			if (t.id != 3)
				throw new SemanticException("THE VARIBLE IS NOT AN ARRAY");
			expr = expression();
			if (expr.sym != 1)
				throw new TypeMismatchedException();
			s.name = s.name + ("[" + expr.name + "]");
			t = t.arrayElement;
			selector(t, s);		//lookahead is "]"
			return ;	
		}
		else
			s.sym = t.id;		
		if (lookahead.sym == Token.RBRACKET)
			lookahead = get_token();
	}
	

	/**
	 * to add statement into the flowchart.
	 * @param t: AbstractStatement
	 */
	public void add_stmt(AbstractStatement t)
	{
		StatementSequence stmt;
		
		if (sign.isEmpty())
		{
			proc.add(t);
		}
		else
		{
			stmt = sign.peek();
			stmt.add(t);
		}	
	}

	
	/**
	 * 
	 * @return Symbol: the token of the OberonScann
	 * @throws Exception
	 */
	public Symbol get_token() throws Exception
	{
		Symbol tem;
		try
		{
			tem = scan.next_token();
			if (tem.sym == Token.LPAREN)
				LeftParen++;
			if (tem.sym == Token.RPAREN)
				RightParen++;
		}
		catch(Exception e)
		{
			throw e;
		}
		return tem;
	}
	
}





