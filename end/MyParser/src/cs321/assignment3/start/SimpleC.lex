package cs321.assignment3.start;
import java_cup.runtime.*; // defines the Symbol class

// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (e.g., literals) also include the value of the token.

class TokenVal {
 // fields
  int linenum;
  int charnum;
 // constructor
  TokenVal(int l, int c) {
    linenum = l;
    charnum = c;
  }
}

class IntLitTokenVal extends TokenVal {
 // new field: the value of the integer literal
  int intVal;
 // constructor
  IntLitTokenVal(int l, int c, int val) {
    super(l,c);
    intVal = val;
  }
}

class BadEscapedChar extends Exception {
}

//TokenVal for both STRINGLITERAL and ID
class StringTokenVal extends TokenVal {
  String strVal;

  StringTokenVal(int l, int c, String s) {
    super(l, c);
    strVal = s;
  }

  //process string s and return a String with all the escaped characters expanded
  //throws BadEscapedChar if a bad escaped character is found
  public static String checkEscapedChars(String s) throws BadEscapedChar {
    // index 0 is the opening quote, so don't include it.
    int start = 1;
    int slash = s.indexOf("\\");
    String strVal = "";
    while (slash != -1) {
      strVal = strVal + s.substring(start, slash);

      // if the slash is the last character in the string then we are done.
      if (slash == s.length() - 1) throw new BadEscapedChar();
      
      char c = s.charAt(slash + 1);
      if (c == 'n') {
        strVal = strVal + "\n";
      } else if (c == 't') {
        strVal = strVal + "\t";
      } else if (c == '"') {
        strVal = strVal + "\"";
      } else if (c == '\\') {
        strVal = strVal + "\\";
      } else if (c == '\'') {
        strVal = strVal + "'";
      } else {
        throw new BadEscapedChar();
      }
      start = slash + 2;
      slash = s.indexOf("\\", slash + 2);
    }
    //the last character is the closing quote, so don't include.
    if (start < s.length() - 1)
      strVal = strVal + s.substring(start, s.length() - 1);
    return strVal;
  }
}


// The following class is used to keep track of the character number at which
// the current token starts on its line.
class CharNum {
  static int num=1;
}
%%
%public
%class Scanner
%implements sym

%line
%column

%cup
%cupdebug

%eofval{
return new Symbol(EOF);
%eofval}


%%

"int" { Symbol s = new Symbol(INT, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 3;
      return s; }

"bool" { Symbol s = new Symbol(BOOL, new TokenVal(yyline+1, CharNum.num));
       CharNum.num += 4;
       return s; }

"void" { Symbol s = new Symbol(VOID, new TokenVal(yyline+1, CharNum.num));
       CharNum.num += 4;
       return s; }

"true" { Symbol s = new Symbol(TRUE, new TokenVal(yyline+1, CharNum.num));
       CharNum.num += 4;
       return s; }

"false" { Symbol s = new Symbol(FALSE, new TokenVal(yyline+1, CharNum.num));
        CharNum.num += 5;
        return s; }

"NULL" { Symbol s = new Symbol(NULL, new TokenVal(yyline+1, CharNum.num));
       CharNum.num += 4;
       return s; }

"if" { Symbol s = new Symbol(IF, new TokenVal(yyline+1, CharNum.num));
     CharNum.num += 2;
     return s; }

"else" { Symbol s = new Symbol(ELSE, new TokenVal(yyline+1, CharNum.num));
       CharNum.num += 4;
       return s; }

"while" { Symbol s = new Symbol(WHILE, new TokenVal(yyline+1, CharNum.num));
        CharNum.num += 5;
        return s; }

"for" { Symbol s = new Symbol(FOR, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 3;
      return s; }

"return" { Symbol s = new Symbol(RETURN, new TokenVal(yyline+1, CharNum.num));
         CharNum.num += 6;
         return s; }

"sizeof" { Symbol s = new Symbol(SIZEOF, new TokenVal(yyline+1, CharNum.num));
         CharNum.num += 6;
         return s; }

"struct" { Symbol s = new Symbol(STRUCT, new TokenVal(yyline+1, CharNum.num));
         CharNum.num += 6;
         return s;
       }

"{" { Symbol s = new Symbol(LCURLY, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }

"}" { Symbol s = new Symbol(RCURLY, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }

"(" { Symbol s = new Symbol(LPAREN, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 1;
      return s; }

")" { Symbol s = new Symbol(RPAREN, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 1;
      return s; }

"[" { Symbol s = new Symbol(LSQBRACKET, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }

"]" { Symbol s = new Symbol(RSQBRACKET, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }

"," { Symbol s = new Symbol(COMMA, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }

"=" { Symbol s = new Symbol(ASSIGN, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }

";" { Symbol s = new Symbol(SEMICOLON, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }

"+=" { Symbol s = new Symbol(PLUSEQL, new TokenVal(yyline+1, CharNum.num));
       CharNum.num += 2;
       return s; }

"-=" { Symbol s = new Symbol(MINUSEQL, new TokenVal(yyline+1, CharNum.num));
     CharNum.num += 2;
     return s; }

"*=" { Symbol s = new Symbol(TIMESEQL, new TokenVal(yyline+1, CharNum.num));
       CharNum.num += 2;
       return s; }
 
"/=" { Symbol s = new Symbol(DIVEQL, new TokenVal(yyline+1, CharNum.num));
     CharNum.num += 2;
     return s; }

"+" { Symbol s = new Symbol(PLUS, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 1;
      return s; }

"-" { Symbol s = new Symbol(MINUS, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }

"*" { Symbol s = new Symbol(TIMES, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 1;
      return s; }
 
"/" { Symbol s = new Symbol(DIVIDE, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }

"!" { Symbol s = new Symbol(NOT, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }

"&"  { Symbol s = new Symbol(ADDROF, new TokenVal(yyline+1, CharNum.num));
     CharNum.num += 1;
     return s; }

"&&" { Symbol s = new Symbol(AND, new TokenVal(yyline+1, CharNum.num));
     CharNum.num += 2;
     return s; }

"||" { Symbol s = new Symbol(OR, new TokenVal(yyline+1, CharNum.num));
       CharNum.num += 2;
       return s; }

"==" { Symbol s = new Symbol(EQUALS, new TokenVal(yyline+1, CharNum.num));
     CharNum.num += 2;
     return s; }

"!=" { Symbol s = new Symbol(NOTEQUALS, new TokenVal(yyline+1, CharNum.num));
     CharNum.num += 2;
     return s; }

"<" { Symbol s = new Symbol(LESS, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }

">" { Symbol s = new Symbol(GREATER, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }

"<=" { Symbol s = new Symbol(LESSEQ, new TokenVal(yyline+1, CharNum.num));
     CharNum.num += 2;
     return s; }

">=" { Symbol s = new Symbol(GREATEREQ, new TokenVal(yyline+1, CharNum.num));
     CharNum.num += 2;
     return s; }

"<<" { Symbol s = new Symbol(WRITE, new TokenVal(yyline+1, CharNum.num));
     CharNum.num += 2;
     return s; }

">>" { Symbol s = new Symbol(READ, new TokenVal(yyline+1, CharNum.num));
     CharNum.num += 2;
     return s; }

"\." { Symbol s = new Symbol(PERIOD, new TokenVal(yyline+1, CharNum.num));
     CharNum.num += 1;
     return s; }

"->" { Symbol s = new Symbol(STRDEREF, new TokenVal(yyline+1, CharNum.num));
       CharNum.num += 2;
       return s; }

"%" { Symbol s = new Symbol(PERCENT, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 1;
      return s; }

[0-9]+ {
   int val;
   try {
     val = (new Integer(yytext())).intValue();
   } catch (NumberFormatException e) {
     Errors.warn(yyline+1, CharNum.num, "integer literal too large; using max value");
     val = Integer.MAX_VALUE;
   }
   Symbol s = new Symbol(INTLITERAL, new IntLitTokenVal(yyline+1, CharNum.num, val));
   CharNum.num += yytext().length();
   return s;
}

[a-zA-Z_][a-zA-Z0-9_]* {
    Symbol s = new Symbol(ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}

\"([^\\\"\n]|\\.)*\" {
    try {
      String str = StringTokenVal.checkEscapedChars(yytext());
      //Symbol s = new Symbol(STRINGLITERAL, new StringTokenVal(yyline+1, CharNum.num, str));
      Symbol s = new Symbol(STRINGLITERAL, new StringTokenVal(yyline+1, CharNum.num, yytext()));
      CharNum.num += yytext().length();
      return s;
    } catch (BadEscapedChar e) {
      Errors.fatal(yyline+1, CharNum.num, "ignoring string literal with bad escaped character");
      CharNum.num += yytext().length();
    }
}

\"([^\\\"\n]|\\.)*\\?$ {
    try {
      StringTokenVal.checkEscapedChars(yytext());
      Errors.fatal(yyline+1, CharNum.num, "ignoring unterminated string literal");
    } catch (BadEscapedChar e) {
      Errors.fatal(yyline+1, CharNum.num, "ignoring unterminated string literal with bad escaped character");
    }
}

\n {CharNum.num = 1;}

\r {CharNum.num = 1;}

[\ \t]+  {CharNum.num += yytext().length(); }

(\/\/|#).* { CharNum.num += yytext().length(); }

. { Errors.fatal(yyline+1, CharNum.num, "ignoring illegal character: " + yytext());
    CharNum.num++; }
