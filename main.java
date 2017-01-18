/** these double asterisk comments are processed by Javadoc, a JDK tool, to generate API docs */
// members of named package cannot access members in the default package
package com.oca.sert;	// must be on top, only one statement will compile

import static com.oca.sert.ClassName.*;	// import all public static members from OTHER package

// top-level class and interface camnnot have defined with protected and private access
// method parameters and local vars cannot be defined using explicit access modifiers
public;	// can be accessed from everywere, different package and not derived classes
protected;	// cannot be accessed from not derived class in different package. Derived class must be public
default;	// access only from the same package

import java.util.Date; import java.sql.Date;	// import both classes in the same class will not compile

import packageName.className;	// packageName -> className -> innerClassName
class newClassName {
	ClassName a;	// will compile
	innerClassName b; }	// will not compile
import packageName.*;	// import all public members, classes and interfaces. Previous will compile

public final class ClassName extends SuperClass implements InterfaceName {}
// access modif optional, nonaccess modif optional, keyword, name, all others are optional

package com.company;	// contents of Multiple.java
// compile error, public interface or class can only be defined in a source code file with a matching name
public interface Printable {}	// will not compile, have to remove access modif or full line
interface Moveable {}
public interface Multiple {}	// will compile if remove

// executable class must be public static void and accept args of String array or varargs of type String
public class ClassName { public static void main(String args[]) { } }	// any valid arg name is ok
public class ClassName { public static void main(String... args) { } }	// any valid arg name is ok

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
