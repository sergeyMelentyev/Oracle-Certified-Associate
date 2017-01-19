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




