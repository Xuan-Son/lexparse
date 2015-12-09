

import java.util.*;

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

abstract class Decl extends AST {

}

class VarDecl extends Decl {
    public VarDecl(Type type, ID id) {
        myType = type;
        myId = id;
    }

    private Type      myType;
    private ID        myId;
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

class ID extends Expr {
    public ID(String strVal) {
        myStrVal = strVal;
    }

    public String getName() {
        return myStrVal;
    }

    private String myStrVal;
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
