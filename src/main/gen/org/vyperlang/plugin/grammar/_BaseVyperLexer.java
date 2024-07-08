// Generated by JFlex 1.9.1 http://jflex.de/  (tweaked for IntelliJ platform)
// source: _BaseVyperLexer.flex

package org.vyperlang.plugin.grammar;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.vyperlang.plugin.psi.VyperTypes.*;


public class _BaseVyperLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\25\u0100\1\u0200\11\u0100\1\u0300\17\u0100\1\u0400\247\u0100"+
    "\10\u0500\u1020\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\2\3\1\4\22\0\1\1\1\5"+
    "\1\6\1\7\1\0\1\10\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\11\24"+
    "\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34"+
    "\2\35\1\36\1\37\1\40\1\41\1\42\4\41\1\43"+
    "\6\41\1\44\3\41\1\45\2\41\1\46\1\47\1\50"+
    "\1\51\1\52\1\0\1\53\1\54\1\55\1\56\1\57"+
    "\1\60\1\61\1\62\1\63\1\41\1\64\1\65\1\66"+
    "\1\67\1\70\1\71\1\41\1\72\1\73\1\74\1\75"+
    "\1\76\1\77\1\100\1\101\1\41\1\102\1\103\1\104"+
    "\1\105\6\0\1\106\32\0\1\107\u01df\0\1\107\177\0"+
    "\13\107\35\0\2\106\5\0\1\107\57\0\1\107\240\0"+
    "\1\107\377\0\u0100\110";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1536];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\2\1\1\3\1\4\1\5\1\1"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\2\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\5\26\1\27\1\1\1\30\1\31\20\26\1\32\1\33"+
    "\1\34\1\35\1\2\1\0\1\36\1\0\1\37\1\0"+
    "\1\40\1\0\1\41\1\0\1\42\1\43\1\44\1\45"+
    "\1\0\1\46\1\0\1\47\2\0\1\50\1\51\1\52"+
    "\1\53\1\54\1\55\4\26\1\56\2\26\1\57\2\0"+
    "\14\26\1\60\1\26\1\61\2\26\1\62\7\26\1\37"+
    "\1\0\1\63\1\0\1\64\1\65\5\26\1\66\1\26"+
    "\1\67\1\0\1\70\1\0\5\26\1\71\5\26\1\72"+
    "\5\26\1\73\1\26\1\74\12\26\1\0\2\26\1\75"+
    "\2\26\1\76\6\26\1\77\1\100\2\26\1\101\1\0"+
    "\3\26\1\102\3\26\1\103\2\26\1\104\6\26\1\105"+
    "\1\0\4\26\1\106\1\107\1\110\3\26\1\111\1\26"+
    "\1\0\10\26\1\112\1\113\3\26\1\114\1\115\3\26"+
    "\1\116\1\117\2\26\1\120\1\26\1\121\2\26\1\122"+
    "\5\26\1\123\1\124\1\125\1\126\1\26\1\127\1\130"+
    "\11\26\1\131\1\132\1\133\1\134\1\135\3\26\1\136"+
    "\2\26\1\137\1\26\1\140\2\26\1\141\1\142\2\26"+
    "\1\143";

  private static int [] zzUnpackAction() {
    int [] result = new int[284];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\111\0\222\0\333\0\u0124\0\u016d\0\u01b6\0\111"+
    "\0\u01ff\0\111\0\111\0\u0248\0\u0291\0\111\0\u02da\0\u0323"+
    "\0\u036c\0\u03b5\0\u03fe\0\111\0\111\0\u0447\0\u0490\0\u04d9"+
    "\0\111\0\111\0\u0522\0\u056b\0\u05b4\0\u05fd\0\u0646\0\111"+
    "\0\u068f\0\111\0\111\0\u06d8\0\u0721\0\u076a\0\u07b3\0\u07fc"+
    "\0\u0845\0\u088e\0\u08d7\0\u0920\0\u0969\0\u09b2\0\u09fb\0\u0a44"+
    "\0\u0a8d\0\u0ad6\0\u0b1f\0\111\0\111\0\111\0\111\0\u0b68"+
    "\0\u068f\0\111\0\u0bb1\0\u0bfa\0\u0c43\0\111\0\u01ff\0\111"+
    "\0\u0c8c\0\111\0\111\0\111\0\111\0\u0cd5\0\u0d1e\0\u0d67"+
    "\0\111\0\u0db0\0\u0df9\0\111\0\111\0\111\0\111\0\111"+
    "\0\111\0\u0e42\0\u0e8b\0\u0ed4\0\u0f1d\0\u068f\0\u0f66\0\u0faf"+
    "\0\u0ff8\0\u1041\0\u108a\0\u10d3\0\u111c\0\u1165\0\u11ae\0\u11f7"+
    "\0\u1240\0\u1289\0\u12d2\0\u131b\0\u1364\0\u13ad\0\u13f6\0\u0522"+
    "\0\u143f\0\u1488\0\u14d1\0\u151a\0\u0522\0\u1563\0\u15ac\0\u15f5"+
    "\0\u163e\0\u1687\0\u16d0\0\u1719\0\111\0\u1762\0\111\0\u17ab"+
    "\0\u17ab\0\u0df9\0\u17f4\0\u183d\0\u1886\0\u18cf\0\u1918\0\u0522"+
    "\0\u1961\0\111\0\u19aa\0\111\0\u19f3\0\u1a3c\0\u1a85\0\u1ace"+
    "\0\u1b17\0\u1b60\0\u0522\0\u1ba9\0\u1bf2\0\u1c3b\0\u1c84\0\u1ccd"+
    "\0\u0522\0\u1d16\0\u1d5f\0\u1da8\0\u1df1\0\u1e3a\0\u0522\0\u1e83"+
    "\0\u0522\0\u1ecc\0\u1f15\0\u1f5e\0\u1fa7\0\u1ff0\0\u2039\0\u2082"+
    "\0\u20cb\0\u2114\0\u215d\0\u21a6\0\u21ef\0\u2238\0\u0522\0\u2281"+
    "\0\u22ca\0\u0522\0\u2313\0\u235c\0\u23a5\0\u23ee\0\u2437\0\u2480"+
    "\0\u0522\0\u0522\0\u24c9\0\u2512\0\u0522\0\u255b\0\u25a4\0\u25ed"+
    "\0\u2636\0\u267f\0\u26c8\0\u2711\0\u275a\0\u0522\0\u27a3\0\u27ec"+
    "\0\u0522\0\u2835\0\u287e\0\u28c7\0\u2910\0\u2959\0\u29a2\0\u0522"+
    "\0\u29eb\0\u2a34\0\u2a7d\0\u2ac6\0\u2b0f\0\u0522\0\u2b58\0\u0522"+
    "\0\u2ba1\0\u2bea\0\u2c33\0\u0522\0\u2c7c\0\u2cc5\0\u2d0e\0\u2d57"+
    "\0\u2da0\0\u2de9\0\u2e32\0\u2e7b\0\u2ec4\0\u2f0d\0\u0522\0\u0522"+
    "\0\u2f56\0\u2f9f\0\u2fe8\0\u29a2\0\111\0\u3031\0\u307a\0\u30c3"+
    "\0\u0522\0\u2b58\0\u310c\0\u3155\0\u0522\0\u319e\0\111\0\u31e7"+
    "\0\u3230\0\u0522\0\u3279\0\u32c2\0\u330b\0\u3354\0\u339d\0\u0522"+
    "\0\u0522\0\u0522\0\u0522\0\u33e6\0\u0522\0\u0522\0\u342f\0\u3478"+
    "\0\u34c1\0\u350a\0\u3553\0\u359c\0\u35e5\0\u362e\0\u3677\0\u0522"+
    "\0\u0522\0\u0522\0\u0522\0\u0522\0\u36c0\0\u3709\0\u3752\0\u0522"+
    "\0\u379b\0\u37e4\0\u0522\0\u382d\0\u0522\0\u3876\0\u38bf\0\u0522"+
    "\0\u0522\0\u3908\0\u3951\0\u0522";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[284];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\4\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\2\33\1\34\1\33\1\35\1\33\1\36\1\33"+
    "\1\37\1\33\1\40\1\41\1\42\1\43\1\33\1\44"+
    "\1\45\1\46\1\47\1\50\1\51\1\33\1\52\1\53"+
    "\1\33\1\54\1\33\1\55\1\56\1\57\1\60\1\61"+
    "\1\33\1\62\1\63\3\33\1\64\1\65\1\66\1\67"+
    "\2\70\1\2\112\0\4\3\42\0\1\71\36\0\2\70"+
    "\31\0\1\72\60\0\2\73\1\0\1\73\1\0\1\73"+
    "\1\74\40\73\1\75\41\73\2\6\3\0\101\6\1\0"+
    "\1\6\31\0\1\76\60\0\2\77\1\0\1\77\1\0"+
    "\5\77\1\100\34\77\1\101\41\77\15\0\1\102\12\0"+
    "\1\103\110\0\1\104\110\0\1\105\101\0\1\106\1\0"+
    "\2\107\25\0\1\110\66\0\1\111\101\0\1\107\1\0"+
    "\2\23\12\0\1\112\5\0\1\113\4\0\1\23\4\0"+
    "\1\112\20\0\1\113\31\0\1\107\1\0\2\23\12\0"+
    "\1\112\12\0\1\23\4\0\1\112\60\0\1\114\1\115"+
    "\110\0\1\116\1\117\107\0\1\120\1\121\102\0\2\33"+
    "\7\0\12\33\4\0\30\33\32\0\2\33\7\0\12\33"+
    "\4\0\27\33\1\122\32\0\2\33\7\0\12\33\4\0"+
    "\1\33\1\123\26\33\32\0\2\33\7\0\12\33\4\0"+
    "\1\33\1\124\26\33\32\0\2\33\7\0\12\33\4\0"+
    "\20\33\1\125\7\33\10\0\1\71\1\126\2\71\127\0"+
    "\2\33\7\0\12\33\4\0\4\33\1\127\10\33\1\130"+
    "\3\33\1\131\6\33\15\0\1\132\3\0\1\133\10\0"+
    "\2\33\7\0\12\33\4\0\16\33\1\134\1\33\1\135"+
    "\6\33\1\136\32\0\2\33\7\0\12\33\4\0\13\33"+
    "\1\137\2\33\1\140\11\33\32\0\2\33\7\0\12\33"+
    "\4\0\5\33\1\141\22\33\32\0\2\33\7\0\12\33"+
    "\4\0\13\33\1\142\10\33\1\143\1\33\1\144\1\33"+
    "\32\0\2\33\7\0\12\33\4\0\16\33\1\145\1\33"+
    "\1\146\7\33\32\0\2\33\7\0\12\33\4\0\5\33"+
    "\1\147\22\33\32\0\2\33\7\0\12\33\4\0\6\33"+
    "\1\150\5\33\1\151\1\152\12\33\32\0\2\33\7\0"+
    "\12\33\4\0\16\33\1\153\11\33\32\0\2\33\7\0"+
    "\12\33\4\0\16\33\1\154\11\33\32\0\2\33\7\0"+
    "\12\33\4\0\20\33\1\155\7\33\32\0\2\33\7\0"+
    "\12\33\4\0\1\33\1\156\21\33\1\157\4\33\32\0"+
    "\2\33\7\0\12\33\4\0\1\33\1\160\3\33\1\161"+
    "\22\33\32\0\2\33\7\0\12\33\4\0\22\33\1\162"+
    "\5\33\32\0\2\33\7\0\12\33\4\0\11\33\1\163"+
    "\16\33\32\0\2\33\7\0\12\33\4\0\11\33\1\164"+
    "\16\33\10\0\4\70\101\0\2\70\1\0\2\73\1\0"+
    "\1\73\1\0\1\73\1\165\40\73\1\75\41\73\6\0"+
    "\1\166\102\0\2\73\3\0\101\73\1\0\1\73\1\0"+
    "\2\77\3\0\101\77\1\0\1\77\22\0\1\167\112\0"+
    "\2\107\12\0\1\112\12\0\1\107\4\0\1\112\54\0"+
    "\2\110\12\0\1\112\12\0\1\110\4\0\1\112\47\0"+
    "\1\170\1\0\1\170\2\0\2\171\25\0\1\171\61\0"+
    "\2\172\7\0\5\172\11\0\7\172\53\0\2\33\7\0"+
    "\12\33\4\0\15\33\1\173\12\33\32\0\2\33\7\0"+
    "\12\33\4\0\13\33\1\174\14\33\32\0\2\33\7\0"+
    "\12\33\4\0\21\33\1\175\6\33\32\0\2\33\7\0"+
    "\12\33\4\0\23\33\1\176\4\33\32\0\2\33\7\0"+
    "\12\33\4\0\4\33\1\177\23\33\32\0\2\33\7\0"+
    "\12\33\4\0\4\33\1\200\23\33\32\0\2\33\7\0"+
    "\12\33\4\0\21\33\1\201\6\33\7\0\2\132\1\0"+
    "\1\132\1\0\1\132\1\202\40\132\1\203\41\132\2\133"+
    "\1\0\1\133\1\0\5\133\1\204\34\133\1\205\41\133"+
    "\23\0\2\33\7\0\12\33\4\0\16\33\1\206\11\33"+
    "\32\0\2\33\7\0\12\33\4\0\5\33\1\207\22\33"+
    "\32\0\2\33\7\0\12\33\4\0\22\33\1\210\5\33"+
    "\32\0\2\33\7\0\12\33\4\0\5\33\1\211\22\33"+
    "\32\0\2\33\7\0\12\33\4\0\15\33\1\212\12\33"+
    "\32\0\2\33\7\0\12\33\4\0\6\33\1\213\10\33"+
    "\1\214\10\33\32\0\2\33\7\0\12\33\4\0\11\33"+
    "\1\215\7\33\1\216\6\33\32\0\2\33\7\0\12\33"+
    "\4\0\5\33\1\217\22\33\32\0\2\33\7\0\12\33"+
    "\4\0\22\33\1\220\5\33\32\0\2\33\7\0\12\33"+
    "\4\0\20\33\1\221\7\33\32\0\2\33\7\0\12\33"+
    "\4\0\16\33\1\222\11\33\32\0\2\33\7\0\12\33"+
    "\4\0\26\33\1\223\1\33\32\0\2\33\7\0\12\33"+
    "\4\0\14\33\1\224\2\33\1\225\10\33\32\0\2\33"+
    "\7\0\12\33\4\0\22\33\1\226\5\33\32\0\2\33"+
    "\7\0\12\33\4\0\7\33\1\227\20\33\32\0\2\33"+
    "\7\0\12\33\4\0\15\33\1\230\4\33\1\231\5\33"+
    "\32\0\2\33\7\0\12\33\4\0\21\33\1\232\5\33"+
    "\1\233\32\0\2\33\7\0\12\33\4\0\2\33\1\234"+
    "\15\33\1\235\7\33\32\0\2\33\7\0\12\33\4\0"+
    "\11\33\1\236\3\33\1\237\12\33\32\0\2\33\7\0"+
    "\12\33\4\0\22\33\1\240\5\33\32\0\2\33\7\0"+
    "\12\33\4\0\20\33\1\241\7\33\32\0\2\33\7\0"+
    "\12\33\4\0\15\33\1\242\12\33\32\0\2\33\7\0"+
    "\12\33\4\0\5\33\1\243\22\33\7\0\6\166\1\244"+
    "\102\166\23\0\2\171\25\0\1\171\61\0\2\33\7\0"+
    "\1\245\11\33\4\0\30\33\32\0\2\33\7\0\12\33"+
    "\4\0\21\33\1\176\6\33\32\0\2\33\7\0\12\33"+
    "\4\0\10\33\1\246\17\33\32\0\2\33\7\0\12\33"+
    "\4\0\5\33\1\247\22\33\32\0\2\33\7\0\12\33"+
    "\4\0\20\33\1\250\7\33\32\0\2\33\7\0\12\33"+
    "\4\0\5\33\1\251\22\33\7\0\2\132\3\0\101\132"+
    "\1\0\1\132\1\0\2\133\3\0\101\133\1\0\1\133"+
    "\24\0\2\33\7\0\12\33\4\0\13\33\1\252\14\33"+
    "\32\0\2\33\7\0\12\33\4\0\1\33\1\253\26\33"+
    "\32\0\2\33\7\0\12\33\4\0\5\33\1\254\22\33"+
    "\32\0\2\33\7\0\12\33\4\0\1\33\1\255\26\33"+
    "\32\0\2\33\7\0\12\33\4\0\21\33\1\256\1\257"+
    "\5\33\32\0\2\33\7\0\12\33\4\0\13\33\1\260"+
    "\14\33\32\0\2\33\7\0\12\33\4\0\6\33\1\261"+
    "\21\33\32\0\2\33\7\0\12\33\4\0\5\33\1\262"+
    "\22\33\32\0\2\33\7\0\12\33\4\0\15\33\1\263"+
    "\12\33\32\0\2\33\7\0\12\33\4\0\5\33\1\264"+
    "\22\33\32\0\2\33\7\0\12\33\4\0\14\33\1\265"+
    "\13\33\15\0\1\266\14\0\2\33\7\0\12\33\4\0"+
    "\30\33\32\0\2\33\7\0\12\33\4\0\23\33\1\267"+
    "\4\33\32\0\2\33\7\0\12\33\4\0\13\33\1\270"+
    "\2\33\1\271\11\33\32\0\2\272\7\0\12\33\4\0"+
    "\5\33\1\273\22\33\32\0\2\33\7\0\12\33\4\0"+
    "\17\33\1\274\1\275\7\33\32\0\2\33\7\0\12\33"+
    "\4\0\21\33\1\276\6\33\32\0\2\33\7\0\12\33"+
    "\4\0\1\33\1\277\26\33\32\0\2\33\7\0\12\33"+
    "\4\0\13\33\1\300\14\33\32\0\2\33\7\0\12\33"+
    "\4\0\5\33\1\301\22\33\32\0\2\33\7\0\12\33"+
    "\4\0\21\33\1\302\6\33\32\0\2\33\7\0\12\33"+
    "\4\0\7\33\1\303\20\33\32\0\2\33\7\0\12\33"+
    "\4\0\23\33\1\304\4\33\32\0\2\33\7\0\12\33"+
    "\4\0\11\33\1\305\11\33\1\306\4\33\32\0\2\33"+
    "\7\0\12\33\4\0\22\33\1\307\5\33\32\0\2\33"+
    "\7\0\12\33\4\0\25\33\1\310\2\33\7\0\6\166"+
    "\1\311\102\166\23\0\2\33\7\0\12\33\4\0\20\33"+
    "\1\312\7\33\32\0\2\33\7\0\7\33\1\313\2\33"+
    "\4\0\30\33\32\0\2\33\7\0\12\33\4\0\5\33"+
    "\1\314\22\33\32\0\2\33\7\0\12\33\4\0\20\33"+
    "\1\315\7\33\32\0\2\33\7\0\12\33\4\0\12\33"+
    "\1\316\15\33\32\0\2\33\7\0\12\33\4\0\21\33"+
    "\1\317\6\33\32\0\2\33\7\0\12\33\4\0\20\33"+
    "\1\320\7\33\32\0\2\33\7\0\12\33\4\0\22\33"+
    "\1\321\5\33\32\0\2\33\7\0\12\33\4\0\11\33"+
    "\1\322\16\33\32\0\2\33\7\0\12\33\4\0\16\33"+
    "\1\323\11\33\32\0\2\33\7\0\12\33\4\0\22\33"+
    "\1\324\5\33\32\0\2\33\7\0\12\33\4\0\20\33"+
    "\1\325\7\33\32\0\2\326\7\0\5\326\12\0\6\326"+
    "\53\0\2\33\7\0\12\33\4\0\22\33\1\327\5\33"+
    "\32\0\2\33\7\0\12\33\4\0\5\33\1\330\22\33"+
    "\32\0\2\33\7\0\12\33\4\0\20\33\1\331\7\33"+
    "\32\0\2\272\7\0\12\33\4\0\30\33\32\0\2\33"+
    "\7\0\12\33\4\0\20\33\1\332\7\33\32\0\2\33"+
    "\7\0\12\33\4\0\1\33\1\333\26\33\32\0\2\33"+
    "\7\0\12\33\4\0\5\33\1\334\22\33\32\0\2\33"+
    "\7\0\12\33\4\0\2\33\1\335\25\33\32\0\2\33"+
    "\7\0\12\33\4\0\11\33\1\336\16\33\32\0\2\33"+
    "\7\0\12\33\4\0\5\33\1\337\22\33\32\0\2\33"+
    "\7\0\12\33\4\0\5\33\1\340\22\33\32\0\2\33"+
    "\7\0\12\33\4\0\20\33\1\341\7\33\32\0\2\33"+
    "\7\0\12\33\4\0\15\33\1\342\12\33\32\0\2\33"+
    "\7\0\12\33\4\0\3\33\1\343\24\33\32\0\2\344"+
    "\7\0\12\33\4\0\30\33\7\0\6\166\1\345\102\166"+
    "\23\0\2\33\7\0\12\33\4\0\20\33\1\346\7\33"+
    "\32\0\2\33\7\0\12\33\4\0\1\33\1\347\26\33"+
    "\32\0\2\33\7\0\12\33\4\0\21\33\1\350\6\33"+
    "\32\0\2\33\7\0\12\33\4\0\22\33\1\351\5\33"+
    "\32\0\2\352\7\0\12\33\4\0\30\33\32\0\2\33"+
    "\7\0\12\33\4\0\1\33\1\353\26\33\32\0\2\33"+
    "\7\0\12\33\4\0\15\33\1\354\12\33\32\0\2\33"+
    "\7\0\12\33\4\0\27\33\1\355\32\0\2\33\7\0"+
    "\12\33\4\0\15\33\1\356\12\33\15\0\1\357\14\0"+
    "\2\326\7\0\5\326\12\0\6\326\53\0\2\33\7\0"+
    "\12\33\4\0\1\33\1\360\26\33\32\0\2\33\7\0"+
    "\12\33\4\0\14\33\1\361\13\33\32\0\2\33\7\0"+
    "\12\33\4\0\22\33\1\362\5\33\32\0\2\33\7\0"+
    "\12\33\4\0\6\33\1\363\6\33\1\364\12\33\32\0"+
    "\2\33\7\0\12\33\4\0\27\33\1\365\32\0\2\33"+
    "\7\0\12\33\4\0\5\33\1\366\22\33\32\0\2\33"+
    "\7\0\12\33\4\0\13\33\1\367\14\33\32\0\2\33"+
    "\7\0\12\33\4\0\3\33\1\370\24\33\32\0\2\33"+
    "\7\0\12\33\4\0\15\33\1\371\12\33\32\0\2\33"+
    "\7\0\12\33\4\0\7\33\1\372\20\33\32\0\2\33"+
    "\7\0\12\33\4\0\22\33\1\373\5\33\32\0\2\33"+
    "\7\0\12\33\4\0\1\33\1\374\26\33\32\0\2\33"+
    "\7\0\12\33\4\0\17\33\1\375\10\33\32\0\2\33"+
    "\7\0\12\33\4\0\21\33\1\376\6\33\32\0\2\33"+
    "\7\0\12\33\4\0\15\33\1\377\12\33\32\0\2\33"+
    "\7\0\12\33\4\0\23\33\1\u0100\4\33\32\0\2\33"+
    "\7\0\12\33\4\0\1\33\1\u0101\26\33\32\0\2\33"+
    "\7\0\12\33\4\0\2\33\1\u0102\25\33\32\0\2\33"+
    "\7\0\12\33\4\0\5\33\1\u0103\22\33\32\0\2\33"+
    "\7\0\12\33\4\0\1\33\1\u0104\26\33\32\0\2\33"+
    "\7\0\12\33\4\0\1\33\1\u0105\26\33\32\0\2\33"+
    "\7\0\12\33\4\0\1\33\1\u0106\26\33\32\0\2\33"+
    "\7\0\12\33\4\0\15\33\1\u0107\12\33\32\0\2\33"+
    "\7\0\12\33\4\0\5\33\1\u0108\22\33\32\0\2\33"+
    "\7\0\12\33\4\0\27\33\1\u0109\32\0\2\33\7\0"+
    "\12\33\4\0\22\33\1\u010a\5\33\32\0\2\33\7\0"+
    "\12\33\4\0\5\33\1\u010b\22\33\32\0\2\33\7\0"+
    "\12\33\4\0\13\33\1\u010c\14\33\32\0\2\33\7\0"+
    "\12\33\4\0\13\33\1\u010d\14\33\32\0\2\33\7\0"+
    "\12\33\4\0\15\33\1\u010e\12\33\32\0\2\33\7\0"+
    "\12\33\4\0\3\33\1\u010f\24\33\32\0\2\33\7\0"+
    "\12\33\4\0\13\33\1\u0110\14\33\32\0\2\33\7\0"+
    "\12\33\4\0\2\33\1\u0111\25\33\32\0\2\33\7\0"+
    "\12\33\4\0\22\33\1\u0112\5\33\32\0\2\33\7\0"+
    "\12\33\4\0\5\33\1\u0113\22\33\32\0\2\33\7\0"+
    "\12\33\4\0\22\33\1\u0114\5\33\32\0\2\33\7\0"+
    "\12\33\4\0\5\33\1\u0115\22\33\32\0\2\33\7\0"+
    "\12\33\4\0\13\33\1\u0116\14\33\32\0\2\33\7\0"+
    "\12\33\4\0\20\33\1\u0117\7\33\32\0\2\33\7\0"+
    "\12\33\4\0\21\33\1\u0118\6\33\32\0\2\33\7\0"+
    "\12\33\4\0\5\33\1\u0119\22\33\32\0\2\33\7\0"+
    "\12\33\4\0\1\33\1\u011a\26\33\32\0\2\33\7\0"+
    "\12\33\4\0\15\33\1\u011b\12\33\32\0\2\33\7\0"+
    "\12\33\4\0\22\33\1\u011c\5\33\7\0";

  private static int [] zzUnpacktrans() {
    int [] result = new int[14746];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\5\1\1\11\1\1\2\11\2\1\1\11"+
    "\5\1\2\11\3\1\2\11\5\1\1\11\1\1\2\11"+
    "\20\1\4\11\1\1\1\0\1\11\1\0\1\1\1\0"+
    "\1\11\1\0\1\11\1\0\4\11\1\0\1\1\1\0"+
    "\1\11\2\0\6\11\10\1\2\0\31\1\1\11\1\0"+
    "\1\11\1\0\11\1\1\11\1\0\1\11\1\0\36\1"+
    "\1\0\21\1\1\0\22\1\1\0\14\1\1\0\16\1"+
    "\1\11\11\1\1\11\55\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[284];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** Number of newlines encountered up to the start of the matched text. */
  @SuppressWarnings("unused")
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  @SuppressWarnings("unused")
  protected int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;

  /* user code: */
  public _BaseVyperLexer() {
    this((java.io.Reader)null);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public _BaseVyperLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { return BAD_CHARACTER;
            }
          // fall through
          case 100: break;
          case 2:
            { return WHITE_SPACE;
            }
          // fall through
          case 101: break;
          case 3:
            { return COMMENT;
            }
          // fall through
          case 102: break;
          case 4:
            { return PERCENT;
            }
          // fall through
          case 103: break;
          case 5:
            { return INTERSECTION;
            }
          // fall through
          case 104: break;
          case 6:
            { return LPAREN;
            }
          // fall through
          case 105: break;
          case 7:
            { return RPAREN;
            }
          // fall through
          case 106: break;
          case 8:
            { return MULT;
            }
          // fall through
          case 107: break;
          case 9:
            { return PLUS;
            }
          // fall through
          case 108: break;
          case 10:
            { return COMMA;
            }
          // fall through
          case 109: break;
          case 11:
            { return MINUS;
            }
          // fall through
          case 110: break;
          case 12:
            { return DOT;
            }
          // fall through
          case 111: break;
          case 13:
            { return DIV;
            }
          // fall through
          case 112: break;
          case 14:
            { return DECIMALNUMBER;
            }
          // fall through
          case 113: break;
          case 15:
            { return COLON;
            }
          // fall through
          case 114: break;
          case 16:
            { return SEMICOLON;
            }
          // fall through
          case 115: break;
          case 17:
            { return LESS;
            }
          // fall through
          case 116: break;
          case 18:
            { return ASSIGN;
            }
          // fall through
          case 117: break;
          case 19:
            { return MORE;
            }
          // fall through
          case 118: break;
          case 20:
            { return QUESTION;
            }
          // fall through
          case 119: break;
          case 21:
            { return DECORATOR;
            }
          // fall through
          case 120: break;
          case 22:
            { return IDENTIFIER;
            }
          // fall through
          case 121: break;
          case 23:
            { return LBRACKET;
            }
          // fall through
          case 122: break;
          case 24:
            { return RBRACKET;
            }
          // fall through
          case 123: break;
          case 25:
            { return CARET;
            }
          // fall through
          case 124: break;
          case 26:
            { return LBRACE;
            }
          // fall through
          case 125: break;
          case 27:
            { return UNION;
            }
          // fall through
          case 126: break;
          case 28:
            { return RBRACE;
            }
          // fall through
          case 127: break;
          case 29:
            { return TILDE;
            }
          // fall through
          case 128: break;
          case 30:
            { return NEQ;
            }
          // fall through
          case 129: break;
          case 31:
            { return STRINGLITERALDOUBLE;
            }
          // fall through
          case 130: break;
          case 32:
            { return PERCENT_ASSIGN;
            }
          // fall through
          case 131: break;
          case 33:
            { return STRINGLITERALSINGLE;
            }
          // fall through
          case 132: break;
          case 34:
            { return EXPONENT;
            }
          // fall through
          case 133: break;
          case 35:
            { return MULT_ASSIGN;
            }
          // fall through
          case 134: break;
          case 36:
            { return PLUS_ASSIGN;
            }
          // fall through
          case 135: break;
          case 37:
            { return MINUS_ASSIGN;
            }
          // fall through
          case 136: break;
          case 38:
            { return FIXEDNUMBER;
            }
          // fall through
          case 137: break;
          case 39:
            { return DIV_ASSIGN;
            }
          // fall through
          case 138: break;
          case 40:
            { return LSHIFT;
            }
          // fall through
          case 139: break;
          case 41:
            { return LESSEQ;
            }
          // fall through
          case 140: break;
          case 42:
            { return EQ;
            }
          // fall through
          case 141: break;
          case 43:
            { return TO;
            }
          // fall through
          case 142: break;
          case 44:
            { return MOREEQ;
            }
          // fall through
          case 143: break;
          case 45:
            { return RSHIFT;
            }
          // fall through
          case 144: break;
          case 46:
            { return BREAK_LINE;
            }
          // fall through
          case 145: break;
          case 47:
            { return AS;
            }
          // fall through
          case 146: break;
          case 48:
            { return IF;
            }
          // fall through
          case 147: break;
          case 49:
            { return IN;
            }
          // fall through
          case 148: break;
          case 50:
            { return OR;
            }
          // fall through
          case 149: break;
          case 51:
            { return ELLIPSIS;
            }
          // fall through
          case 150: break;
          case 52:
            { return SCIENTIFICNUMBER;
            }
          // fall through
          case 151: break;
          case 53:
            { return HEXNUMBER;
            }
          // fall through
          case 152: break;
          case 54:
            { return AND;
            }
          // fall through
          case 153: break;
          case 55:
            { return STRINGLITERALDOUBLEB;
            }
          // fall through
          case 154: break;
          case 56:
            { return STRINGLITERALSINGLEB;
            }
          // fall through
          case 155: break;
          case 57:
            { return DEF;
            }
          // fall through
          case 156: break;
          case 58:
            { return FOR;
            }
          // fall through
          case 157: break;
          case 59:
            { return LOG;
            }
          // fall through
          case 158: break;
          case 60:
            { return NOT;
            }
          // fall through
          case 159: break;
          case 61:
            { return BOOLEANLITERAL;
            }
          // fall through
          case 160: break;
          case 62:
            { return BOOL;
            }
          // fall through
          case 161: break;
          case 63:
            { return ELIF;
            }
          // fall through
          case 162: break;
          case 64:
            { return ELSE;
            }
          // fall through
          case 163: break;
          case 65:
            { return FROM;
            }
          // fall through
          case 164: break;
          case 66:
            { return INTM;
            }
          // fall through
          case 165: break;
          case 67:
            { return PASS;
            }
          // fall through
          case 166: break;
          case 68:
            { return PURE;
            }
          // fall through
          case 167: break;
          case 69:
            { return VIEW;
            }
          // fall through
          case 168: break;
          case 70:
            { return BREAK;
            }
          // fall through
          case 169: break;
          case 71:
            { return BYTES;
            }
          // fall through
          case 170: break;
          case 72:
            { return CLEAR;
            }
          // fall through
          case 171: break;
          case 73:
            { return EVENT;
            }
          // fall through
          case 172: break;
          case 74:
            { return RAISE;
            }
          // fall through
          case 173: break;
          case 75:
            { return RANGE;
            }
          // fall through
          case 174: break;
          case 76:
            { return UINTM;
            }
          // fall through
          case 175: break;
          case 77:
            { return MULTILINESTRINGTOKEN;
            }
          // fall through
          case 176: break;
          case 78:
            { return ASSERT;
            }
          // fall through
          case 177: break;
          case 79:
            { return BYTESM;
            }
          // fall through
          case 178: break;
          case 80:
            { return DEPLOY;
            }
          // fall through
          case 179: break;
          case 81:
            { return HEXLITERAL;
            }
          // fall through
          case 180: break;
          case 82:
            { return IMPORT;
            }
          // fall through
          case 181: break;
          case 83:
            { return PUBLIC;
            }
          // fall through
          case 182: break;
          case 84:
            { return RETURN;
            }
          // fall through
          case 183: break;
          case 85:
            { return STRING;
            }
          // fall through
          case 184: break;
          case 86:
            { return STRUCT;
            }
          // fall through
          case 185: break;
          case 87:
            { return HASHMAP;
            }
          // fall through
          case 186: break;
          case 88:
            { return ADDRESS;
            }
          // fall through
          case 187: break;
          case 89:
            { return PAYABLE;
            }
          // fall through
          case 188: break;
          case 90:
            { return DYNARRAY;
            }
          // fall through
          case 189: break;
          case 91:
            { return CONSTANT;
            }
          // fall through
          case 190: break;
          case 92:
            { return CONTINUE;
            }
          // fall through
          case 191: break;
          case 93:
            { return EXTERNAL;
            }
          // fall through
          case 192: break;
          case 94:
            { return INTERNAL;
            }
          // fall through
          case 193: break;
          case 95:
            { return IMMUTABLE;
            }
          // fall through
          case 194: break;
          case 96:
            { return INTERFACE;
            }
          // fall through
          case 195: break;
          case 97:
            { return IMPLEMENTS;
            }
          // fall through
          case 196: break;
          case 98:
            { return NONPAYABLE;
            }
          // fall through
          case 197: break;
          case 99:
            { return NONREENTRANT;
            }
          // fall through
          case 198: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
