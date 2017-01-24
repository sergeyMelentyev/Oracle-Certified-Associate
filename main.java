/** these double asterisk comments are processed by Javadoc, a JDK tool, to generate API docs */
// members of named package cannot access members in the default package
package com.oca.sert;	// must be on top, only one statement will compile
import java.util.Date; import java.sql.Date;	// import both classes in the same class will not compile

import static com.oca.sert.ClassName.*;	// import all public static members from OTHER package

package com.company;	// contents of Multiple.java
// compile error, public interface or class can only be defined in a source code file with a matching name
public interface Printable {}	// will not compile, have to remove access modif or full line
interface Moveable {}
public interface Multiple {}	// will compile if remove

// executable class must be public static void and accept args of String array or varargs of type String
public class ClassName { public static void main(String args[]) { } }	// any valid arg name is ok
public class ClassName { public static void main(String... args) { } }	// any valid arg name is ok

import packageName.className;	// packageName -> className -> innerClassName
class newClassName {
	ClassName a;	// will compile
	innerClassName b; }	// will not compile
import packageName.*;	// import all public members, classes and interfaces. Previous will compile



/* ACCESS MODIFIER */
// top-level class and interface camnnot have defined with protected and private access
// method parameters and local vars cannot be defined using explicit access modifiers
public final class ClassName extends SuperClass implements InterfaceName {}
// access modif optional, nonaccess modif optional, keyword, name, all others are optional
public;	// can be accessed from everywere, different package and not derived classes
protected;	// cannot be accessed from not derived class in different package
default;	// access only from the same package



/* ABSTRACT MODIFIER */
// none of the different types of variable can be defined as abstract, will not compile
abstract class ClassName {}	// cannot be instantiated, may not containe any abstract methods
interface InterfaceName {}	// abstract keyword is allowed but redundant



/* FINAL MODIFIER */
// class, method or variable can be final. Cannot be used with the declaration of an interface
final class ClassName {}	// cannot be extended
final int i;	// val assigne once via declaration or in constructor calling methods that change its state
final methodName() {}	// cannot be overridden by a derived class



/* STATIC MODIFIER */
// class, method (not top level), variable or interface (not top level) can be static. 
// nonprivate static vars and methods can be inherited, cannot be overrided but can be redefined
static int i;	// shared with all instances, can be accessed when no instances have been created
static void methodName(){}	// cannot access instance vars, only static.
ClassName name = null; name.staticVarName;	// can access static var and methods using null reference



/* PRIMITIVE DATA TYPE */
Numetic; Signed; Integers; byte, short, int, long;	// byte -128 to 127 inclusive
Numetic; Signed; Floating-Point; float, double;
Numetic; Unsigned; Character; char;	// only positive
Boolean; boolean;
int octal = 0413; int hex = 0x10B; int binary = 0b10010;
int octal = 04_13; int hex = 0x10_BA_75; int binary = 0b1_0000_10;	// valid literal values

byte b = 10; short s = b + b;	// will not compile, possible lossy conversion, b will be widened to int
final byte b = 10; short s = b + b;	// will compile.

// postfix and prefix operators behave the same if not part of an expression
int a = 10; a = a++ + a + a-- - a-- + ++a;	// a = 10 + 11 + 11 - 10 + 10;

/* Precedence of operators
	exp++, exp--
	++exp, --exp, +exp, -exp
	*, /, %
	+, -
	<, >, <=, >=
	==, !=
	&&
	||
	=, +=, -=, *=, /=, %=
*/



/* OBJECT REFERENCE VARIABLE */
// store the address of the obj they refer to
ClassName className = new ClassName();	// Type of obj reference var, name of obj reference var



/* WRAPPER CLASSES. IMMUTABLE OBJ */
// Object -> Boolean, Character
// Object -> Number -> Byte, Short, Integer, Long, Float, Double
Boolean bool = true; Byte b = 10;	// autoboxing
Boolean bool = new Boolean(true); Byte b = new Byte((byte)10);	// constructor with primitive values
Boolean bool = new Boolean("true"); Byte b = new Byte("10");	// constructor with string values
Boolean bool = Boolean.valueOf(true); Byte b = Byte.valueOf(10);	// static mehod
booleanValue(); charValue(); byteValue();	// retreive primitive value
parseBoolean(String s); parseByte(String s);	// get primitive val from string, EXEPT for Character
// all parse methods throws NumberFormat-Exception except Boolean.parseBoolean(), that returns false

Boolean bool;	// cached instance exist for the values true and false
Character ch;	// cached instance exist for the values 0 to 127
Byte, Short, Integer, Long;	// cached instance exist for the values -128 to 127
Integer iOne = new Integer(10);	// always create new instances (no cached values)
Integer iTwo = Integer.valueOf(10);	// returns a cached copy (-128 to 127 range, exept for float and double)



/* COMPARISON */
public boolean equals(Object obj); // compare primitive value stored by a wrapper class
public int compareTo(Double value);	// indicates whether some other object is "equal to" this one
objOne == objTwo;	// compare obj reference only with same obj wrapper class, or will not compile



/* CLASSES. METHODS */
// overloaded methods: same name, different method agr list, any return value, any access level
void methodName(int...days) {	// only one varargs in a perameter, must be last, it works like an array
	for (int i = 0; i < days.length; i++)
		System.out.println(days[i]);
}



/* CLASSES. CONSTRUCTORS */
class ClassName {
	String name; int i;	// instance variables or object fields

	static { int i; }	// static initialization block, will be executed first
	{ int i; }	// initializtion block, will be executed second

	ClassName() {	// invokes constructor that accepts two args, will be executed last
		this(null, 0);	// MUST BE the first statement in constructor
	}
	ClassName(String s, int i) {	// overloaded constructor, will be executed last
		this.name = s; this.i = i; 
	}
	
	
}



class ClassName {
	String name;	// instance variable will save state for only one specific object
	ClassName (String name){
		this.name = name;
	}
}

interface InterfaceName {
	static methodName(){};
	default methodName(){};
}

