{
(*******************************************************************)
(*  This is part of SymbolicKAT, it is distributed under the       *)
(*  terms of the GNU Lesser General Public License version 3       *)
(*           (see file LICENSE for more details)                   *)
(*                                                                 *)
(*  Copyright 2014: Damien Pous. (CNRS, LIP - ENS Lyon, UMR 5668)  *)
(*******************************************************************)
open Parser
}

let blank = [ ' ' '\t' ]+
let newline = ['\r' '\n' ] | "\r\n"
let var = [ 'k'-'z' ]
let prd = [ 'a'-'j' ]

rule token = parse
  | newline                       { Common.next_line lexbuf; token lexbuf }
  | blank                         { token lexbuf }
  | ';'                           { SEP }
  | '<' | "<="                    { LE }
  | '>' | "=>"                    { GT }
  | '='                           { EQ }
  | '0'                           { ZER }
  | '1'                           { ONE }
  | var as v                      { VAR v }
  | prd as v                      { PRD v }
  | '+'                           { PLS }
  | '*'                           { STR }
  | '!'                           { NEG }
  | '('                           { LPAR }
  | ')'                           { RPAR }
  | _                             { Common.unexpected_char lexbuf }
  | eof                           { EOF }
