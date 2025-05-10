package users;

public class Parent extends User {

	public Parent(String name) {
		super(name);
	}

	@Override
	public void update(String message) {
		System.out.println(name+ ": "+ message);		
	}

}
