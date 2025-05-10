package users;

public class Child extends User{

	public Child(String name) {
		super(name);
	}

	@Override
	public void update(String message) {
		System.out.println(name+ ": "+ message);		
	}

}
