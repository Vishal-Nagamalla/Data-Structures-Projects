public class Student{

	private String firstName;
	private String lastName;
	private String noun;
	private String oneThing;

	public Student(String f, String l, String n, String o){
		this.firstName = f.trim();
		this.lastName = l.trim();
		this.noun = n.trim();
		this.oneThing = o.trim();
	}

	public String getfirstName(){
		return firstName;
	}

	public String getlastName(){
		return lastName;
	}

	public String getnoun(){
		return noun;
	}

	public String getoneThing(){
		return oneThing;
	}

	public String toString(){
		return firstName + " " + lastName;
	}
}