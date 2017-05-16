/*
 * To change this license header): choose License Headers in Project Properties.
 * To change this template file): choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Interface.Events;

/**
 *
 * @author alleew
 */
public class KeyboardEvent {
    
    public java.lang.Object me_ = null;
    
    public final static int ANY_KEY = -1;
    public final static int NUM_0 = 7;
    public final static int NUM_1 = 8;
    public final static int NUM_2 = 9;
    public final static int NUM_3 = 10;
    public final static int NUM_4 = 11;
    public final static int NUM_5 = 12;
    public final static int NUM_6 = 13;
    public final static int NUM_7 = 14;
    public final static int NUM_8 = 15;
    public final static int NUM_9 = 16;
    public final static int A = 29;
    public final static int ALT_LEFT = 57;
    public final static int ALT_RIGHT = 58;
    public final static int APOSTROPHE = 75;
    public final static int B = 30;
    public final static int BACKSLASH = 73;
    public final static int C = 31;
    public final static int CAPS_LOCK = 77;
    public final static int CLEAR = 28;
    public final static int COMMA = 55;
    public final static int D = 32;
    public final static int BACKSPACE = 67;
    public final static int FORWARD_DELETE = 112;
    public final static int DOWN = 20;
    public final static int LEFT = 21;
    public final static int RIGHT = 22;
    public final static int UP = 19;
    public final static int E = 33;
    public final static int ENTER = 66;
    public final static int EQUALS = 70;
    public final static int F = 34;
    public final static int G = 35;
    public final static int GRAVE = 68;
    public final static int H = 36;
    public final static int HOME = 3;
    public final static int I = 37;
    public final static int J = 38;
    public final static int K = 39;
    public final static int L = 40;
    public final static int LEFT_BRACKET = 71;
    public final static int M = 41;
    public final static int META_LEFT = 63;
    public final static int META_RIGHT = 64;
    public final static int MINUS = 69;
    public final static int N = 42;
    public final static int NUM_LOCK = 78;
    public final static int O = 43;
    public final static int P = 44;
    public final static int PAUSE = 197;
    public final static int PERIOD = 56;
    public final static int POWER = 26;
    public final static int PRINT_SCREEN = 183;
    public final static int Q = 45;
    public final static int R = 46;
    public final static int RIGHT_BRACKET = 72;
    public final static int S = 47;
    public final static int SCROLL_LOCK = 80;
    public final static int SEMICOLON = 74;
    public final static int SHIFT_LEFT = 59;
    public final static int SHIFT_RIGHT = 60;
    public final static int SLASH = 76;
    public final static int SPACE = 62;
    public final static int T = 48;
    public final static int TAB = 61;
    public final static int U = 49;
    public final static int UNKNOWN = 0;
    public final static int V = 50;
    public final static int W = 51;
    public final static int X = 52;
    public final static int Y = 53;
    public final static int Z = 54;
    public final static int CONTROL_LEFT = 129;
    public final static int CONTROL_RIGHT = 130;
    public final static int ESCAPE = 131;
    public final static int END = 132;
    public final static int INSERT = 133;
    public final static int PAGE_UP = 92;
    public final static int PAGE_DOWN = 93;
    public final static int NUMPAD_0 = 144;
    public final static int NUMPAD_1 = 145;
    public final static int NUMPAD_2 = 146;
    public final static int NUMPAD_3 = 147;
    public final static int NUMPAD_4 = 148;
    public final static int NUMPAD_5 = 149;
    public final static int NUMPAD_6 = 150;
    public final static int NUMPAD_7 = 151;
    public final static int NUMPAD_8 = 152;
    public final static int NUMPAD_9 = 153;
    public final static int NUMPAD_DECIMAL = 83;
    public final static int NUMPAD_ENTER = 156;
    public final static int NUMPAD_MINUS = 82;
    public final static int NUMPAD_PLUS = 81;
    public final static int NUMPAD_SLASH = 181;
    public final static int NUMPAD_STAR = 17;
    public final static int NUMPAD_EQUALS = 84;
    public final static int COLON = 243;
    public final static int F1 = 244;
    public final static int F2 = 245;
    public final static int F3 = 246;
    public final static int F4 = 247;
    public final static int F5 = 248;
    public final static int F6 = 249;
    public final static int F7 = 250;
    public final static int F8 = 251;
    public final static int F9 = 252;
    public final static int F10 = 253;
    public final static int F11 = 254;
    public final static int F12 = 255;
    
    public String ToTextNative(int keycode)
    {
        switch(keycode)
        {
            case (UNKNOWN): return "Unknown";
            case (HOME): return "Home";
            case (NUM_0): return "0";
            case (NUM_1): return "1";
            case (NUM_2): return "2";
            case (NUM_3): return "3";
            case (NUM_4): return "4";
            case (NUM_5): return "5";
            case (NUM_6): return "6";
            case (NUM_7): return "7";
            case (NUM_8): return "8";
            case (NUM_9): return "9";
            case (UP): return "Up";
            case (DOWN): return "Down";
            case (LEFT): return "Left";
            case (RIGHT): return "Right";
            case (POWER): return "Power";
            case (CLEAR): return "Clear";
            case (A): return "a";
            case (B): return "b";
            case (C): return "c";
            case (D): return "d";
            case (E): return "e";
            case (F): return "f";
            case (G): return "g";
            case (H): return "h";
            case (I): return "i";
            case (J): return "j";
            case (K): return "k";
            case (L): return "l";
            case (M): return "m";
            case (N): return "n";
            case (O): return "o";
            case (P): return "p";
            case (Q): return "q";
            case (R): return "r";
            case (S): return "s";
            case (T): return "t";
            case (U): return "u";
            case (V): return "v";
            case (W): return "w";
            case (X): return "x";
            case (Y): return "y";
            case (Z): return "z";
            case (BACKSPACE): return "Backspace";
            case (COMMA): return ",";
            case (PERIOD): return ".";
            case (ALT_LEFT): return "Left Alt";
            case (ALT_RIGHT): return "Right Alt";
            case (SHIFT_LEFT): return "Left Shift";
            case (SHIFT_RIGHT): return "Right Shift";
            case (TAB): return "Tab";
            case (SPACE): return "Space";
            case (META_LEFT): return "Left Meta";
            case (META_RIGHT): return "Right Meta";
            case (ENTER): return "Enter";
            case (GRAVE): return "`";
            case (MINUS): return "-";
            case (EQUALS): return "=";
            case (LEFT_BRACKET): return "[";
            case (RIGHT_BRACKET): return "]";
            case (BACKSLASH): return "\\";
            case (SEMICOLON): return ";";
            case (APOSTROPHE): return "'";
            case (SLASH): return "/";
            case (NUM_LOCK): return "Num Lock";
            case (CAPS_LOCK): return "Caps Lock";
            case (SCROLL_LOCK): return "Scroll Lock";
            case (PAGE_UP): return "Page Up";
            case (PAGE_DOWN): return "Page Down";
            case (PAUSE): return "Pause";
            case (PRINT_SCREEN): return "Print Screen";
            case (FORWARD_DELETE): return "Forward Delete";
            case (CONTROL_LEFT): return "Left Control";
            case (CONTROL_RIGHT): return "Right Control";
            case (ESCAPE): return "Escape";
            case (END): return "End";
            case (INSERT): return "Insert";
            case (NUMPAD_0): return "Numpad0";
            case (NUMPAD_1): return "Numpad1";
            case (NUMPAD_2): return "Numpad2";
            case (NUMPAD_3): return "Numpad3";
            case (NUMPAD_4): return "Numpad4";
            case (NUMPAD_5): return "Numpad5";
            case (NUMPAD_6): return "Numpad6";
            case (NUMPAD_7): return "Numpad7";
            case (NUMPAD_8): return "Numpad8";
            case (NUMPAD_9): return "Numpad9";
            case (NUMPAD_DECIMAL): return ".";
            case (NUMPAD_PLUS): return "+";
            case (NUMPAD_ENTER): return "Enter";
            case (NUMPAD_MINUS): return "-";
            case (NUMPAD_SLASH): return "/";
            case (NUMPAD_STAR): return "*";
            case (NUMPAD_EQUALS): return "=";
            case (COLON): return ":";
            case (F1): return "F1";
            case (F2): return "F2";
            case (F3): return "F3";
            case (F4): return "F4";
            case (F5): return "F5";
            case (F6): return "F6";
            case (F7 ): return "F7";
            case (F8): return "F8";
            case (F9): return "F9";
            case (F10): return "F10";
            case (F11): return "F11";
            case (F12): return "F12";
        }
        return "Unknown";
    }
    
    public String ToTextShiftNative(int keycode)
    {
        switch(keycode)
        {
            case (UNKNOWN): return "Unknown";
            case (HOME): return "Home";
            case (NUM_0): return ")";
            case (NUM_1): return "!";
            case (NUM_2): return "@";
            case (NUM_3): return "#";
            case (NUM_4): return "$";
            case (NUM_5): return "%";
            case (NUM_6): return "^";
            case (NUM_7): return "&";
            case (NUM_8): return "*";
            case (NUM_9): return "(";
            case (UP): return "Up";
            case (DOWN): return "Down";
            case (LEFT): return "Left";
            case (RIGHT): return "Right";
            case (POWER): return "Power";
            case (CLEAR): return "Clear";
            case (A): return "A";
            case (B): return "B";
            case (C): return "C";
            case (D): return "D";
            case (E): return "E";
            case (F): return "F";
            case (G): return "G";
            case (H): return "H";
            case (I): return "I";
            case (J): return "J";
            case (K): return "K";
            case (L): return "L";
            case (M): return "M";
            case (N): return "N";
            case (O): return "O";
            case (P): return "P";
            case (Q): return "Q";
            case (R): return "R";
            case (S): return "S";
            case (T): return "T";
            case (U): return "U";
            case (V): return "V";
            case (W): return "W";
            case (X): return "X";
            case (Y): return "Y";
            case (Z): return "Z";
            case (COMMA): return "<";
            case (PERIOD): return ">";
            case (ALT_LEFT): return "Left Alt";
            case (ALT_RIGHT): return "Right Alt";
            case (SHIFT_LEFT): return "Left Shift";
            case (SHIFT_RIGHT): return "Right Shift";
            case (TAB): return "Tab";
            case (SPACE): return "Space";
            case (META_LEFT): return "Left Meta";
            case (META_RIGHT): return "Right Meta";
            case (ENTER): return "Enter";
            case (BACKSPACE): return "Backspace";
            case (GRAVE): return "~";
            case (MINUS): return "_";
            case (EQUALS): return "+";
            case (LEFT_BRACKET): return "{";
            case (RIGHT_BRACKET): return "}";
            case (BACKSLASH): return "|";
            case (SEMICOLON): return ":";
            case (APOSTROPHE): return "\"";
            case (SLASH): return "?";
            case (NUM_LOCK): return "Num Lock";
            case (CAPS_LOCK): return "Caps Lock";
            case (SCROLL_LOCK): return "Scroll Lock";
            case (PAGE_UP): return "Page Up";
            case (PAGE_DOWN): return "Page Down";
            case (PAUSE): return "Pause";
            case (PRINT_SCREEN): return "Print Screen";
            case (FORWARD_DELETE): return "Forward Delete";
            case (CONTROL_LEFT): return "Left Control";
            case (CONTROL_RIGHT): return "Right Control";
            case (ESCAPE): return "Escape";
            case (END): return "End";
            case (INSERT): return "Insert";
            case (NUMPAD_0): return "Numpad0";
            case (NUMPAD_1): return "Numpad1";
            case (NUMPAD_2): return "Numpad2";
            case (NUMPAD_3): return "Numpad3";
            case (NUMPAD_4): return "Numpad4";
            case (NUMPAD_5): return "Numpad5";
            case (NUMPAD_6): return "Numpad6";
            case (NUMPAD_7): return "Numpad7";
            case (NUMPAD_8): return "Numpad8";
            case (NUMPAD_9): return "Numpad9";
            case (NUMPAD_DECIMAL): return ".";
            case (NUMPAD_ENTER): return "Enter";
            case (NUMPAD_PLUS): return "+";
            case (NUMPAD_MINUS): return "-";
            case (NUMPAD_SLASH): return "/";
            case (NUMPAD_STAR): return "*";
            case (NUMPAD_EQUALS): return "=";
            case (COLON): return ":";
            case (F1): return "F1";
            case (F2): return "F2";
            case (F3): return "F3";
            case (F4): return "F4";
            case (F5): return "F5";
            case (F6): return "F6";
            case (F7 ): return "F7";
            case (F8): return "F8";
            case (F9): return "F9";
            case (F10): return "F10";
            case (F11): return "F11";
            case (F12): return "F12";
        }
        return "Unknown";
    }
}
