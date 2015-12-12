

import java.util.*;
import java.io.PrintWriter;
import java.lang.String;

abstract class AST {
    public abstract void printTree(PrintWriter pw, int mySpace);
}

class Program extends AST {
    public Program(DeclList L) {
        myDeclList = L;
    }
    private DeclList myDeclList;
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("Program");
    	myDeclList.printTree(pw, mySpace + 4);
    }
}

class DeclList extends AST {
    public DeclList(LinkedList<?> S) {
        myDecls = S;
    }

    protected LinkedList<?> myDecls;
    
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("DeclList");
    	for (int i = 0; i < myDecls.size(); ++i)
    	{
    		((AST)myDecls.get(i)).printTree(pw, mySpace + 4);
    	}
    }
}

class StructDeclList extends DeclList {
    public StructDeclList(LinkedList<?> S) {
        super(S);
    }
}

class FormalsList extends AST {
    public FormalsList(LinkedList<?> S) {
        myFormals = S;
    }

    private LinkedList<?> myFormals;
    
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("formals");
    	mySpace += 4;
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("LPAREN");
    	for (int i = 0; i < myFormals.size(); ++i)
    	{
    		((AST)myFormals.get(i)).printTree(pw, mySpace);
    	}
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("RPAREN");
    }
}

class FuncBody extends AST {
    public FuncBody(DeclList declList, StmtList stmtList) {
        myDeclList = declList;
        myStmtList = stmtList;
    }

    private DeclList myDeclList;
    private StmtList myStmtList;
    
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("funcBody");
    	mySpace += 4;
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("LCURLY");
    	myDeclList.printTree(pw, mySpace);
    	myStmtList.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("RCURLY");
    		
    }
}

class StmtList extends AST {
    public StmtList(LinkedList<?> S) {
        myStmts = S;
    }

    private LinkedList<?> myStmts;
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("StmtList");
    	for (int i = 0; i < myStmts.size(); ++i)
    	{
    		((AST)myStmts.get(i)).printTree(pw, mySpace + 4);
    	}
    }
}

class ExprList extends AST {
	public ExprList(LinkedList<?> S) {
		myStmts = S;
	}
	
	private LinkedList<?> myStmts;
	public void printTree(PrintWriter pw, int mySpace)
	{
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("actualList");
		for (int i = 0; i < myStmts.size(); ++i)
		{
			((AST)myStmts.get(i)).printTree(pw, mySpace + 4);
		}
	}
}

abstract class Decl extends AST {
}

class VarDecl extends Decl {
	public VarDecl(Type type, ID id) {
        myType = type;
        myId = id;
        myVal = -1;
    }
    public VarDecl(Type type, ID id, int val) {
        myType = type;
        myId = id;
        myVal = val;
    }

    private Type      myType;
    private ID        myId;
    private int		  myVal;
    
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("varDecl");
    	mySpace += 4;
    	myType.printTree(pw, mySpace);
    	myId.printTree(pw, mySpace);
    	if (myVal != -1)
    	{
    		for (int i = 0; i < mySpace; ++i)
    		{
    			pw.print(" ");
    		}
    		pw.println("LSQBRACKET");
    		for (int i = 0; i < mySpace; ++i)
    		{
    			pw.print(" ");
    		}
    		pw.print("INTLITERAL (");
    		pw.print(myVal);
    		pw.println(")");
    		for (int i = 0; i < mySpace; ++i)
    		{
    			pw.print(" ");
    		}
    		pw.println("RSQBRACKET");
    	}
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
    	pw.println("SEMICOLON");
    }
}

class FuncDef extends Decl {
    public FuncDef(Type type, int numPtr, ID id, FormalsList formalList,
            FuncBody body) {
        myType = type;
        myId = id;
        myFormalsList = formalList;
        myBody = body;
    }

    private Type        myType;
    private ID          myId;
    private FormalsList myFormalsList;
    private FuncBody    myBody;
    
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("funcDef");
    	mySpace += 4;
    	myType.printTree(pw, mySpace);
    	myId.printTree(pw, mySpace);
    	myFormalsList.printTree(pw, mySpace);
    	myBody.printTree(pw, mySpace);
    }
}

class FormalDecl extends Decl {
    public FormalDecl(Type type, ID id, int ptrs) {
        myType = type;
        myId = id;
    }

    private Type myType;
    private ID   myId;
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("formalDecl");
    	myType.printTree(pw, mySpace + 4);
    	myId.printTree(pw, mySpace + 4);
    }
}

class FuncDecl extends Decl {
	public FuncDecl(Type type, ID id, FormalsList fm) {
		myType = type;
		myId = id;
		myFm = fm;
	}
	
	private Type myType;
    private ID   myId;
    private FormalsList myFm;
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("funcDecl");
    	myType.printTree(pw, mySpace + 4);
    	myId.printTree(pw, mySpace + 4);
    	myFm.printTree(pw, mySpace + 4);
    	for (int i = 0; i < mySpace + 4; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("SEMICOLON");
    }
}

class StructDecl extends Decl {
	public StructDecl(ID id, StructDeclList sl)
	{
		myId = id;
		mySl = sl;
	}
	
	private ID myId;
	private StructDeclList mySl;
	public void printTree(PrintWriter pw, int mySpace)
	{
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("structDecl");
		mySpace += 4;
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("STRUCT");
		myId.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("LCURLY");
		mySl.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("RCURLY");
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("SEMICOLON");
	}
}

// **********************************************************************
// Type
// **********************************************************************
abstract class Type extends AST {

    abstract public String name();
	public void printTree(PrintWriter pw, int mySpace)
	{
		    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.print("TYPE (");
	}
}

class IntType extends Type {
    public IntType() {
    }

    public String name() {
        return "INT";
    }
    public void printTree(PrintWriter pw, int mySpace)
    {
		super.printTree(pw, mySpace);
		pw.print(name());
    	pw.println(")");
    }
}

class VoidType extends Type {
    public VoidType() {
    }

    public String name() {
        return "VOID";
    }
    
    public void printTree(PrintWriter pw, int mySpace)
    {
		super.printTree(pw, mySpace);
		pw.print(name());
    	pw.println(")");
    }
}

class BoolType extends Type {
    public BoolType() {
    }

    public String name() {
        return "BOOL";
    }
    
    public void printTree(PrintWriter pw, int mySpace)
    {
		super.printTree(pw, mySpace);
		pw.print(name());
    	pw.println(")");
    }
}

class StructType extends Type {
    public StructType(ID id) {
    	myId = id;
    }

    public String name() {
        return "STRUCT";
    }
    
    private ID myId;
    
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println(name());
    	myId.printTree(pw, mySpace);
    }
}



// **********************************************************************
// Expr
// **********************************************************************

abstract class Expr extends AST {

}

class IntLiteral extends Expr {
    public IntLiteral(int intVal) {
        myIntVal = intVal;
    }

    private int myIntVal;
    
    public void printTree(PrintWriter pw, int mySpace)
    {
    	pw.print(" ");
    	pw.print("INTLITERAL (");
    	pw.print(myIntVal);
    	pw.println(")");
    }
}

class DoubleLiteral extends Expr {
    public DoubleLiteral(double doubleVal) {
        myDoubleVal = doubleVal;
    }

    private double myDoubleVal;
    
    public void printTree(PrintWriter pw, int mySpace)
    {
    	pw.print(" ");
    	pw.print("DOUBLELITERAL (");
    	pw.print(myDoubleVal);
    	pw.println(")");
    }
}

class StringLiteral extends Expr {
    public StringLiteral(String stringVal) {
        myStringVal = stringVal;
    }

    private String myStringVal;
    
    public void printTree(PrintWriter pw, int mySpace)
    {
    	pw.print(" ");
    	pw.print("STRINGLITERAL (");
    	pw.print(myStringVal);
    	pw.println(")");
    }
}

class NullExpr extends IntLiteral {
	public NullExpr() {
		super(0);
	}
}

class SizeOfExpr extends Expr {
	public SizeOfExpr(ID id)
	{
		myId = id;
	}
	
	private ID myId;
	
	public void printTree(PrintWriter pw, int mySpace)
	{
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("SIZEOF");
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("LPAREN");
		myId.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("RPAREN");
	}
}

class BooleanExpr extends Expr {
	public BooleanExpr(Boolean b)
	{
		myB = b;
	}
	
	private Boolean myB;
	public void printTree(PrintWriter pw, int mySpace)
	{
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		if (myB == true)
			pw.println("TRUE");
		else
			pw.println("FALSE");
	}
}

class ID extends Expr {
    public ID(String strVal) {
        myStrVal = strVal;
    }

    public String getName() {
        return myStrVal;
    }

    private String myStrVal;
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.print("ID (");
		pw.print(myStrVal);
		pw.println(")");
    }
}

class ArrayExpr extends Expr {
	public ArrayExpr(Expr e1, Expr e2)
	{
		myE1 = e1;
		myE2 = e2;
		e1 = null;
	}
	
	public void printTree(PrintWriter pw, int mySpace)
	{
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("arrayExpr");
		mySpace += 4;
		myE1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("LSQBRACKET");
		myE2.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("RSQBRACKET");
	}
	
	private Expr myE1;
	private Expr myE2;
}

class StructRef extends Expr {
	public StructRef(Expr ex, ID id)
	{
		myId = id;
		myEx = ex;
	}
	
	private Expr myEx;
	private ID myId;
	
	public void printTree(PrintWriter pw, int mySpace)
	{
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("loc");
		mySpace += 4;
		myEx.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("PERIOD");
		myId.printTree(pw, mySpace);
	}
}

abstract class BinaryExpr extends Expr {
    public BinaryExpr(Expr exp1, Expr exp2) {
        myExp1 = exp1;
        myExp2 = exp2;
    }

    protected Expr myExp1;
    protected Expr myExp2;
}

class PlusExpr extends BinaryExpr {
    public PlusExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }
	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("PLUS");
		myExp2.printTree(pw, mySpace);
	}
}

class MinusExpr extends BinaryExpr {
    public MinusExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("MINUS");
		myExp2.printTree(pw, mySpace);
	}
    
}

class TimesExpr extends BinaryExpr {
    public TimesExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }
    
	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("TIMES");
		myExp2.printTree(pw, mySpace);
	}

}

class DivideExpr extends BinaryExpr {
    public DivideExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }
    
	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("DIVIDE");
		myExp2.printTree(pw, mySpace);
	}

}

class PercentExpr extends BinaryExpr {
    public PercentExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }
    
	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("PERCENT");
		myExp2.printTree(pw, mySpace);
	}

}

class AndExpr extends BinaryExpr {
    public AndExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }
    
	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("AND");
		myExp2.printTree(pw, mySpace);
	}

}

class OrExpr extends BinaryExpr {
    public OrExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }
    
	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("OR");
		myExp2.printTree(pw, mySpace);
	}

}

class EqualsExpr extends BinaryExpr {
    public EqualsExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }
    
	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("EQUALS");
		myExp2.printTree(pw, mySpace);
	}

}

class NotEqualsExpr extends BinaryExpr {
    public NotEqualsExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }
    
	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("NOTEQUALS");
		myExp2.printTree(pw, mySpace);
	}

}

class LessExpr extends BinaryExpr {
    public LessExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }
    
	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("LESS");
		myExp2.printTree(pw, mySpace);
	}

}

class GreaterExpr extends BinaryExpr {
    public GreaterExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }
    
	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("GREATER");
		myExp2.printTree(pw, mySpace);
	}

}

class LessEqExpr extends BinaryExpr {
    public LessEqExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }
    
	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("LESSEQ");
		myExp2.printTree(pw, mySpace);
	}

}

class GreaterEqExpr extends BinaryExpr {
    public GreaterEqExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }
    
	public void printTree(PrintWriter pw, int mySpace)
	{
		myExp1.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("GREATEREQ");
		myExp2.printTree(pw, mySpace);
	}

}

class CallExpr extends Expr
{
	public CallExpr(ID id)
	{
		myId = id;
		myEl = null;
	}
	
	public CallExpr(ID id, ExprList el)
	{
		myId = id;
		myEl = el;
	}
	
	private ID myId;
	private ExprList myEl;
	
	public void printTree(PrintWriter pw, int mySpace)
	{
		myId.printTree(pw, mySpace);
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("LPAREN");
		if (myEl != null)
		{
			myEl.printTree(pw, mySpace);
		}
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("RPAREN");
	}
}

class UnaryExpr extends Expr
{
	public UnaryExpr(Expr ex)
	{
		myEx = ex;
	}
	
	protected Expr myEx;
	public void printTree(PrintWriter pw, int mySpace){}
}

class AddrOfExpr extends UnaryExpr
{
	public AddrOfExpr(Expr ex)
	{
		super(ex);
	}
	
	public void printTree(PrintWriter pw, int mySpace)
	{
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("ADDROF");
		myEx.printTree(pw, mySpace);
		
	}
}

class NotExpr extends UnaryExpr
{
	public NotExpr(Expr ex)
	{
		super(ex);
	}
	
	public void printTree(PrintWriter pw, int mySpace)
	{
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("NOT");
		myEx.printTree(pw, mySpace);
		
	}
}

class UnaryMinusExpr extends UnaryExpr
{
	public UnaryMinusExpr(Expr ex)
	{
		super(ex);
	}
	
	public void printTree(PrintWriter pw, int mySpace)
	{
		for (int i = 0; i < mySpace; ++i)
		{
			pw.print(" ");
		}
		pw.println("MINUS");
		myEx.printTree(pw, mySpace);
		
	}
}

// **********************************************************************
// Stmt
// **********************************************************************

abstract class Stmt extends AST {

}

class AssignStmt extends Stmt {
    public AssignStmt(Expr lhs, Expr exp, int dm) {
        myLhs = lhs;
        myExp = exp;
		myDm = dm;
    }

    private Expr myLhs;
    private Expr myExp;
	private int myDm;
    
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("assignStmt");
    	mySpace += 4;
    	myLhs.printTree(pw, mySpace);
		if (myDm == 1)
		{
			for (int i = 0; i < mySpace; ++i)
			{
				pw.print(" ");
			}
			pw.println("ASSIGN");
		}
    	myExp.printTree(pw, mySpace);
    }
}

class IfStmt extends Stmt {
	public IfStmt(Expr ex, DeclList sdl, StmtList sl)
	{
		myEx = ex;
		mySdl = sdl;
		mySl = sl;
	}
	
	private Expr myEx;
	private DeclList mySdl;
	private StmtList mySl;
	
    public void printTree(PrintWriter pw, int mySpace)
    {
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("ifStmt");
    	mySpace += 4;
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("IF");
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("LPAREN");
    	myEx.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("RPAREN");
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("LCURLY");
    	mySdl.printTree(pw, mySpace);
    	mySl.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("RCURLY");
    }
}

class IfElseStmt extends Stmt {
	public IfElseStmt(Expr ex, DeclList sdl1, StmtList s1, DeclList sdl2, StmtList s2)
	{
		myEx = ex;
		mySdl1 = sdl1;
		mySdl2 = sdl2;
		myS1 = s1;
		myS2 = s2;
	}
	
	private Expr myEx;
	private DeclList mySdl1;
	private DeclList mySdl2;
	private StmtList myS1;
	private StmtList myS2;
	
	public void printTree(PrintWriter pw, int mySpace)
	{
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("ifElseStmt");
    	mySpace += 4;
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("IF");
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("LPAREN");
    	myEx.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("RPAREN");
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("LCURLY");
    	mySdl1.printTree(pw, mySpace);
    	myS1.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("RCURLY");
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("ELSE");
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("LCURLY");
    	mySdl2.printTree(pw, mySpace);
    	myS1.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("RCURLY");
	}
}

class WhileStmt extends Stmt {
	public WhileStmt(Expr ex, DeclList sdl, StmtList sl)
	{
		myEx = ex;
		mySdl = sdl;
		mySl = sl;
	}
	
	private Expr myEx;
	private DeclList mySdl;
	private StmtList mySl;
	
	public void printTree(PrintWriter pw, int mySpace)
	{
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("whileStmt");
    	mySpace += 4;
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("WHILE");
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("LPAREN");
    	myEx.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("RPAREN");
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("LCURLY");
    	mySdl.printTree(pw, mySpace);
    	mySl.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("RCURLY");
	}
}

class ReturnStmt extends Stmt {
	public ReturnStmt(Expr ex)
	{
		myEx = ex;
	}
	
	private Expr myEx;
	public void printTree(PrintWriter pw, int mySpace)
	{
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("returnStmt");
    	mySpace += 4;
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("RETURN");
    	myEx.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("SEMICOLON");
	}
}

class ForStmt extends Stmt {
	public ForStmt(Stmt init, Expr cond, Stmt incre, DeclList dl, StmtList sl)
	{
		myInit = init;
		myCond = cond;
		myIncre = incre;
		myDl = dl;
		mySl = sl;
	}
	
	private Stmt myInit;
	private Expr myCond;
	private Stmt myIncre;
	private DeclList myDl;
	private StmtList mySl;
	
	public void printTree(PrintWriter pw, int mySpace)
	{
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("forStmt");
    	mySpace += 4;
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("FOR");
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("LPAREN");
    	myInit.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("SEMICOLON");
    	myCond.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("SEMICOLON");
    	myIncre.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("RPAREN");
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("LCURLY");
    	myDl.printTree(pw, mySpace);
    	mySl.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("RCURLY");
	}
}

class CallStmt extends Stmt {
	public CallStmt(CallExpr ce)
	{
		myCe = ce;
	}
	
	private CallExpr myCe;
	
	public void printTree(PrintWriter pw, int mySpace)
	{
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("callStmt");
    	mySpace += 4;
    	myCe.printTree(pw, mySpace);
    	for (int i = 0; i < mySpace; ++i)
    	{
    		pw.print(" ");
    	}
    	pw.println("SEMICOLON");
	}
}
