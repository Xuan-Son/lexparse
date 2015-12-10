

import java.util.*;
import java.lang.String;

abstract class AST {
    
}

class Program extends AST {
    public Program(DeclList L) {
        myDeclList = L;
    }
    private DeclList myDeclList;
}

class DeclList extends AST {
    public DeclList(LinkedList<?> S) {
        myDecls = S;
    }

    protected LinkedList<?> myDecls;
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
}

class FuncBody extends AST {
    public FuncBody(DeclList declList, StmtList stmtList) {
        myDeclList = declList;
        myStmtList = stmtList;
    }

    private DeclList myDeclList;
    private StmtList myStmtList;
}

class StmtList extends AST {
    public StmtList(LinkedList<?> S) {
        myStmts = S;
    }

    private LinkedList<?> myStmts;
}

class ExprList extends AST {
	public ExprList(LinkedList<?> S) {
		myStmts = S;
	}
	
	private LinkedList<?> myStmts;
}

abstract class Decl extends AST {

}

class VarDecl extends Decl {
	public VarDecl(Type type, ID id) {
        myType = type;
        myId = id;
    }
    public VarDecl(Type type, ID id, int val) {
        myType = type;
        myId = id;
        myVal = val;
    }

    private Type      myType;
    private ID        myId;
    private int		  myVal;
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
}

class FormalDecl extends Decl {
    public FormalDecl(Type type, ID id, int ptrs) {
        myType = type;
        myId = id;
    }

    private Type myType;
    private ID   myId;
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
}

class StructDecl extends Decl {
	public StructDecl(ID id, StructDeclList sl)
	{
		myId = id;
		mySl = sl;
	}
	
	private ID myId;
	private StructDeclList mySl;
}

// **********************************************************************
// Type
// **********************************************************************
abstract class Type extends AST {

    abstract public String name();
}

class IntType extends Type {
    public IntType() {
    }

    public String name() {
        return "INT";
    }
}

class VoidType extends Type {
    public VoidType() {
    }

    public String name() {
        return "VOID";
    }
}

class BoolType extends Type {
    public BoolType() {
    }

    public String name() {
        return "BOOL";
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
}

class DoubleLiteral extends Expr {
    public DoubleLiteral(double doubleVal) {
        myDoubleVal = doubleVal;
    }

    private double myDoubleVal;
}

class StringLiteral extends Expr {
    public StringLiteral(String stringVal) {
        myStringVal = stringVal;
    }

    private String myStringVal;
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
}

class BooleanExpr extends Expr {
	public BooleanExpr(Boolean b)
	{
		myB = b;
	}
	
	private Boolean myB;
}

class ID extends Expr {
    public ID(String strVal) {
        myStrVal = strVal;
    }

    public String getName() {
        return myStrVal;
    }

    private String myStrVal;
}

class ArrayExpr extends Expr {
	public ArrayExpr(Expr e1, Expr e2)
	{
		myE1 = e1;
		myE2 = e2;
		e1 = null;
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
}

class MinusExpr extends BinaryExpr {
    public MinusExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

}

class TimesExpr extends BinaryExpr {
    public TimesExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

}

class DivideExpr extends BinaryExpr {
    public DivideExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

}

class PercentExpr extends BinaryExpr {
    public PercentExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

}

class AndExpr extends BinaryExpr {
    public AndExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

}

class OrExpr extends BinaryExpr {
    public OrExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

}

class EqualsExpr extends BinaryExpr {
    public EqualsExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

}

class NotEqualsExpr extends BinaryExpr {
    public NotEqualsExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

}

class LessExpr extends BinaryExpr {
    public LessExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

}

class GreaterExpr extends BinaryExpr {
    public GreaterExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

}

class LessEqExpr extends BinaryExpr {
    public LessEqExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

}

class GreaterEqExpr extends BinaryExpr {
    public GreaterEqExpr(Expr exp1, Expr exp2) {
        super(exp1, exp2);
    }

}

class CallExpr extends Expr
{
	public CallExpr(ID id)
	{
		myId = id;
	}
	
	public CallExpr(ID id, ExprList el)
	{
		myId = id;
		myEl = el;
	}
	
	private ID myId;
	private ExprList myEl;
}

class UnaryExpr extends Expr
{
	public UnaryExpr(Expr ex)
	{
		myEx = ex;
	}
	
	protected Expr myEx;
}

class AddrOfExpr extends UnaryExpr
{
	public AddrOfExpr(Expr ex)
	{
		super(ex);
	}
}

class NotExpr extends UnaryExpr
{
	public NotExpr(Expr ex)
	{
		super(ex);
	}
}

class UnaryMinusExpr extends UnaryExpr
{
	public UnaryMinusExpr(Expr ex)
	{
		super(ex);
	}
}

// **********************************************************************
// Stmt
// **********************************************************************

abstract class Stmt extends AST {

}

class AssignStmt extends Stmt {
    public AssignStmt(Expr lhs, Expr exp) {
        myLhs = lhs;
        myExp = exp;
    }

    private Expr myLhs;
    private Expr myExp;
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
}

class ReturnStmt extends Stmt {
	public ReturnStmt(Expr ex)
	{
		myEx = ex;
	}
	
	private Expr myEx;
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
}

class CallStmt extends Stmt {
	public CallStmt(CallExpr ce)
	{
		myCe = ce;
	}
	
	private CallExpr myCe;
}
