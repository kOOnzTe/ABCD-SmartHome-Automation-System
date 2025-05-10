package users;

public class Admin extends User {
    public Admin(String name) {
        super(name);
    }

	@Override
	public void update(String message) {
		System.out.println(name+ ": "+ message);
		
	}

    

}