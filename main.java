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



/* CLASSES */
// all top level types (classes, enums, interfaces) can be declared only public or default
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



/* CLASSES. METHODS. CHAINED METHODS EVALS FROM LEFT TO RIGHT */
// overloaded methods: same name, different method agr list, any return value, any access level
// overridden methods: same name, same method arg list, different method body
void methodName(int...days) {	// only one varargs in a perameter, must be last, it works like an array
	for (int i = 0; i < days.length; i++)
		System.out.println(days[i]);
}



/* CLASSES. INHERITANCE */
// derived class does not inherit private members, default access members if super class in separate package
// does not inherit constructors of the super class

// can implement 2+ interfaces with static methods, does not matter same name or signature or return type

// can implement 2+ interfaces with same constant name if call to these values is not ambiguous
interface Jump { int MIN = 10; } 
interface Move { int MIN = 10; }	// same constant name
class ClassName implements Jump, Move {}	// will compile, no ambiguous implicit refarence to MIN const
class ClassName implements Jump, Move { ClassName() { int i = MIN; } }	// will not compile, is ambiguous
class ClassName implements Jump, Move { ClassName() { int i = Jump.MIN; } }	// will compile

// can implement 2+ interfaces with same method names if same signature or form an overloaded set of methods
interface Jump { String methodName(); } 
interface Move { void methodName(); }	// return value differ
class ClassName implements Jump, Move { String methodName() { return ""; } }	// will not compile

// must override default implementation of the same named DEFAULT methods in 2+ implemented interfaces
interface Jump { default void methodName() {;} } 
interface Move { default void methodName() {;} }	// same default methods
class ClassName implements Jump, Move {}	// will not compile
class ClassName implements Jump, Move { void methodName() {;} }	// will compile



/* CLASSES. INTERFACES */
// cannot define constructors
// can define the default implementation for its methods and static methods, cannot be instantiated
// members are vars, methods, inner interfaces, inner classes. Only public and default access

// can extend 2+ interfaces with static methods, does not matter same name or signature or return type

// interface can extend 2+ interfaces with same abstruct method names ONLY without methods body
interface InterfaceOne { String getName(); }
interface InterfaceTwo { String getName(); }
interface InterfaceFinal extends InterfaceOne, InterfaceTwo {}
class ClassName implements InterfaceFinal { String getName() { return ""; } }	// will compile

// interface can extend 2+ interfaces with default methods name ONLY overridding these methods
interface InterfaceOne { default void getName() {;} }
interface InterfaceTwo { default void getName() {;} }
interface InterfaceFinal extends InterfaceOne, InterfaceTwo { default void getName() {;} }	// must override

// can access method in superinterface using super key word
interface InterfaceOne { default void getName() {;} }
interface InterfaceTwo { default void getName() {;} }
interface InterfaceFinal extends InterfaceOne, InterfaceTwo { InterfaceTwo.super.getName(); }

interface InterfaceName {	// all methods and vars are implicitly public
	static final int CONST = 1;	// must be initialized
	static methodName() {};	// can be accessed as InterfaceName.methodName or by name of implemented class
	default methodName() {};
}
class ClassName implements InterfaceName {
	public methodName() {};		// must implement interface methods using public access
}

// this access to current object, super access to superClass object (both excluded static fields and methods)
interface InterfaceName {
	int MIN = 9999; 
	String printFunc();
	default void status() {
		System.out.println(this);
		System.out.println(this.MIN);
		System.out.println(this.printFunc());
	}
}
class ClassName implements InterfaceName {
	public String printFunc() { return ("anyText" + this); }
}
InterfaceName refVal = new ClassName();
refVal.status();	// ClassName@19e0bfd 9999 anyTextClassName@19e0bfd



/* CLASSES. REFERENCE VARIABLES */
interface InterfaceName {}
class SuperClass {}
class SubClass extends SuperClass implements InterfaceName {}
class SubNewClass extends SuperClass implements InterfaceName {}

// access using its own class, can access all vars and methods in SuperClass and InterfaceName
SubClass refVar = new SubClass();	// var of type SubClass can be used to refer to its object

// access using refVar of type SuperClass, cannot access vars and methods in SubClass and InterfaceName
SuperClass refVar = new SubClass();	// reference var of type SuperClass can be used to refer to its object

// access using refVar of type InterfaceName, cannot access vars and methods in SubClass and SuperClass
InterfaceName refVar = new SubClass();	// reference var of type InterfaceName can be used

// array of objects grouped by a common base class or an interface
InterfaceName[] arr = new InterfaceName[2];
InterfaceName[0] = new SubClass(); InterfaceName[1] = new SubNewClass();



/* CLASSES. CASTING */
interface InterfaceName { void methodName(); }
class SuperClass {}
class SubClass extends SuperClass implements InterfaceName { int field; void methodName() {;} }
InterfaceName refVar = new SubClass();
// next line will not compile, refVar is of type InterfaceName and that interface does not define field
refVar.field = 10;
((SubClass)refVar).field = 10;	// will compile



/* CLASSES. POLYMORPHISM */
// method with the same signature (name and method parameters)
// works only with overridden methods (same number and type of method arguments)



/* STRING IMMUTABLE OBJECT */
public final class String extends Object implements Serializable, Comparable<String>, CharSequence {}
// store value in fixed size, private final char[] value;

static String format(String format, Object... args);
static String valueOf(Object obj);	// return str representation of the obj arg
static String valueOf(char[] data, int offset, int count);
String intern();	// return str with the same content, guaranteed to be from a pool of unique strs

// overloaded String constructor can accept char array, StringBuiler and StringBuffer objects
char[] c = new char[]{'s'}; String strOne = new String(c);
StringBuilder sb = new StringBuilder("s"); String strOne = new String(sb);
StringBuffer sb = new StringBuffer("s"); String strOne = new String(sb);

int length(); boolean isEmpty();
String toLowerCase(); String toUpperCase();
int indexOf(int ch); int indexOf(int ch, int fromIndex);
int indexOf(String subStr); int indexOf(String subStr, int fromIndex);
int lastIndexOf(int ch); int lastIndexOf(int ch, int fromIndex);
int lastIndexOf(String str); int lastIndexOf(String str, int fromIndex);
char charAt(int i);
void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin);	// copies chars into new char array

boolean equals(Object arg); boolean equalsIgnoreCase(String arg); // true if same sequence of chars
boolean contentEquals(CharSequence arg);	// CharBuffer, Segment, String, StringBuffer, StringBuilder
boolean contains(CharSequence s);	// true if this str contains the specified sequence of chars
int compareTo(String arg);	// 0 if equals, -1 if str < arg (lexicographically), 1 if str > arg
int compareToIgnoreCase(String str);	// compares 2 strs lexicographically, ignoring case differences

boolean regionMatches(int toffset, String other, int ooffset, int len);	// substring compare
boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len);
boolean startsWith(String prefix); boolean startsWith(String prefix, int toffset);
boolean endsWith(String suffix);

String substring(int beginI); String substring(int beginI, int endI); // new str, endIndex not included
String concat(String str);	// concatenates the specified string to the end of this string
String replace(char oldChar, char newChar);	// new str resulting from replacing oldChar with newChar
String replace(CharSequence target, CharSequence replacement);
String trim();	// returns a copy of the st, with no leading and trailing whitespace

String toString(); char[] toCharArray();

// string comparison
String strOne = new String("s"); String strTwo = new String("s");	// create new Str obj not pooled
strOne == strTwo;	// false, compares the addresses of the objects
String strOne = "s"; String strTwo = "s";	// initialize new Str obj and store in a str pool
strOne == strTwo;	// true



/* STRINGBUILDER MUTABLE OBJECT */
abstract class AbstractStringBuilder implements Appendable, CharSequence {}
// store value in none fixed size, char[] value; int count;
StringBuilder sb = new StringBuilder(); // StringBuilder() { val = new char[16]; }
StringBuilder sb = new StringBuilder(""); // StringBuilder(String s) { val = new char[s.length() + 16]; }

StringBuilder append(Object obj); // String, StringBuffer, CharSequence, char[], boolean, char, int...
StringBuilder append(CharSequence s, int start, int end);
StringBuilder append(char[] str, int offset, int len);

StringBuilder insert(int index, char[] str, int offset, int len);
StringBuilder insert(int offset, Object obj); // String, char[], CharSequence, boolean, char, int...

StringBuilder delete(int start, int end); StringBuilder deleteCharAt(int index); // not included end pos

StringBuilder reverse();	// sequence will be replaced by the reverse of the sequence

StringBuilder replace(int start, int end, String str);	// not included end pos
CharSequence subSequence(int start, int end);	// does not modify existing value
/* STRINGBUFFER MUTABLE OBJECT WITH SYNCHRONIZED METHODS */



/* ARRAY IMMUTABLE OBJECT */
public class Arrays extends Object {}
// arr of objs stores a collection of heap-memory addresses or pointers
String[] arr = new String[2];	// array allocation with size, cannot expend or reduce
String[] arr = new String[] {"", ""};	// arr declaration, allocation and initialization
String[] arr = {"", ""};

MyInterface[] arr = new MyInterface[obj];	// null or obj that implement the relevant interface type
MyAbsClass[] arr = new MyAbsClass[obj];	// null or obj that extends the relevant abstract class
Object[] arr = new Object[new MyClass(), null, new Integer[7]];	// any data type

static void sort(int[] a);	// ascending, any array data type
static void sort(int[] a, int fromIndex, int toIndex);

static boolean equals(int[] a, int[] b);	// any data type, elem1.equals(elem2), same size
static boolean deepEquals(Object[] a1, Object[] a2);	// use with nested arrays of arbitrary depth

static void fill(int[] a, int val);	// fill arr with val, any data type
static void fill(int[] a, int fromIndex, int toIndex, int val);

static int[] copyOf(int[] original, int newLength);	// copy array, truncating or padding with zeros
static int[] copyOfRange(int[] original, int from, int to);	// copy specified range into a new array

static <T>List<T> asList(T... a);	// returned list is serializable and implements RandomAccess
static String toString(int[] a);	// string representation
static String deepToString(Object[] a);	// use with nested arrays of arbitrary depth



/* COLLECTION. ARRAYLIST MUTABLE OBJECT*/
class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {}
// size(), isEmpty(), get(), set(), iterator(), listIterator() run in constant time
// add() runs in amortized constant time, adding n elements requires O(n) time, all of other linear time
int	size(); boolean	isEmpty(); void clear(); Object[] toArray(); boolean contains(Object o);

boolean	add(E e); void add(int index, E elem);	// append at the end or inserts at the specified position
boolean	addAll(Collection<? extends E> c); boolean addAll(int i, Collection<? extends E> c);	// collection

E remove(int index);	// removes the element at the specified position in this list
boolean	remove(Object o);	// removes the first occurrence of the specified element from this list
boolean	removeAll(Collection<?> c); // removes from this list all elems from the specified collection
Object clone();	// returns a shallow copy of this ArrayList instance, objects will stay the same

E set(int index, E element); // replace elem at the specified position with the specified element

void ensureCapacity(int minCapacity);	// increases the capacity of this ArrayList instance

void forEach(Consumer<? super E> action); // performs action for each element of the Iterable
E get(int index);	// get elem at position
int	indexOf(Object o);	// returns the index of the first occurrence of the specified element
int	lastIndexOf(Object o);	// returns the index of the last occurrence

Iterator<E>	iterator();	// returns an iterator over the elements in this list in proper sequence
ListIterator<E>	listIterator(); // returns a list iterator over the elements in this list
void sort(Comparator<? super E> c); // sorts list according to the order induced by the comparator

ArrayList<String> arr = new ArrayList<>();
ListIterator<String> iter = arr.listIterator(); while (iter.hasNext()) { iterator.next(); }



/* DATE IMMUTABLE OBJECT, THREAD SAFE */
final class LocalDate extends Object implements Temporal,TemporalAdjuster,ChronoLocalDate,Serializable {}
LocalDate d = LocalDate.of(2017, 01, 27); LocalDate.of(2017, Month.JANUARY, 27);
LocalDate d = LocalDate.now(); LocalDate.parse("2017-01-27");	// current, only two digits vals xxxx-XX-XX
d.getDayOfMonth(); d.getDayOfWeek(); d.getDayOfYear(); d.getMonth(); d.getMonthValue(); d.getYear();
d.minusDays(long); d.minusMonths(long); d.minusWeeks(long); d.minusYears(long);	// d.plusXXX(long);
boolean	isAfter(ChronoLocalDate other);	// checks if this date is after the specified date
boolean	isBefore(ChronoLocalDate other);	//checks if this date is before the specified date

final class LocalTime extends Obj implements Temporal,TemporalAdjuster,Comparable<LocalTime>,Serializable {}
LocalTime d = LocalTime.of(120, 12);	// hh,mm; hh,mm,ss; hh,mm,ss,ns;
LocalTime d = LocalTime.now(); LocalDate.parse("15:08:23");	// 00-23 hours time standard, only two digits
d.getHour(); d.getMinute(); d.getSecond(); d.getNano();
d.minusHours(long); d.minusMinutes(long); d.minusSeconds(long); d.minusNanos(long);	// d.plusXXX(long);
boolean	isAfter(LocalTime other);	// checks if this time is after the specified time
boolean	isBefore(LocalTime other);	// checks if this time is before the specified time

final class LocalDateTime implements Temporal,TemporalAdjuster,ChronoLocalDateTime<LocalDate>,Serializable{}

final class Period extends Object implements ChronoPeriod, Serializable {}
// can be negative

final class DateTimeFormatter extends Object {}



/* CONTROL FLOW */
final int i;
switch() {	// char, byte, short, int, String, Integer, Short, Byte, Character, enum, must not be null
	case 1*1: /*logic here*/ ; break;	// expression allowed, must be compile time constants
	case i+i: /*logic here*/ ; break;	// allowed
}	

for (int i = 0; i < arr.length(); ++i, methodName()) {}	// increment block can call methods



/* LAMBDA EXPRESSIONS */
// will work only with functional interface, that defines exactly one abstruct method
(paramName) -> paramName.methodName() >= value;
(paramType paramName) -> { return (paramName.methodName() >= value); };

interface Validate { boolean check(Emp emp); }	// functional interface with one abstruct method
public interface Predicate<T> { boolean check(T t); }	// functional, generic interface

class Emp {
	String name; int performanceRating; double salary;
	Emp (String nm, int r, double sl) { this.name = nm; this.performanceRating = r; this.salary = sl; }
	String getName() { return this.name; }
	int getPerformanceRation() { return this.performanceRating; }
	String getSalary() { return this.salary; }
}

static void filterWithFunctional(ArrayList<Emp> list, Validate rule) {
	for (Emp e : list)
		if (rule.check(e))
			System.out.println(e);
}
static void filterWithPredicate(ArrayList<Emp> list, Predicate<Emp> rule) {
	for (Emp e : list)
		if (rule.check(e))
			System.out.println(e);
}


Emp e1 = new Emp("Sergey", 5, 9999.00); Emp e2 = new Emp("Olga", 6, 10000.00);
ArrayList<Emp> arr = new ArrayList<>(); arr.add(e1); arr.add(e2);

Validate validPerfor = e -> e.getPerformanceRation() >= 5;
filterWithFunctional(arr, validPerfor);

Predicate<Emp> predicate = e -> e.getPerformanceRation() >= 5;
filterWithPredicate(arr, predicate);



/* EXCEPTIONS */
// checked exception

// runtime unchecked exception

// errors unchecked


