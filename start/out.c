Program
    DeclList
        funcDef
            TYPE (VOID)
            ID (main)
            formals
                LPAREN
                RPAREN
            funcBody
                LCURLY
                DeclList
                    varDecl
                        TYPE (INT)
                        ID (a)
                        SEMICOLON
                    varDecl
                        TYPE (INT)
                        ID (b)
                        SEMICOLON
                    varDecl
                        TYPE (INT)
                        ID (c)
                        SEMICOLON
                    varDecl
                        TYPE (INT)
                        ID (d)
                        SEMICOLON
                StmtList
                    assignStmt
                        ID (c)
                        ASSIGN
                        ID (a)
                        DIVIDE
                        ID (b)
                    assignStmt
                        ID (d)
                        ASSIGN
                        ID (a)
                        TIMES
                        ID (b)
                    assignStmt
                        ID (a)
                        ASSIGN
                        ID (b)
                        PLUS
                        MINUS
                        ID (c)
                RCURLY
