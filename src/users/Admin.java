package users;

public final class Admin {
    private static User instance;

    private Admin() {
    }

    public static synchronized User getInstance() {
        if (instance == null) {
            System.out.println("No admin is assigned. Please set an admin first using setInstance().");
            return null; 
        }
        return instance;
    }

	public static void setInstance(User newAdminCandidate) {
        if (newAdminCandidate == null) {
            System.out.println("Error: Cannot set a null admin.");
            return;
        }
        // Check the candidate admin, not the current static instance
        if (newAdminCandidate.isChild) { 
            System.out.println("Error: Admin cannot be a child! " + newAdminCandidate.getName() + " was not set as Admin.");
        } else {
            if (instance != null) {
                System.out.println("Warning: Overwriting existing admin (" + instance.getName() + ") with new admin (" + newAdminCandidate.getName() + ").");
            }
            instance = newAdminCandidate;
            System.out.println(instance.getName() + " has been set as the Admin.");
        }
	}

    // Optional: Method to clear the admin if needed
    public static void clearInstance() {
        if (instance != null) {
            System.out.println("Admin instance for " + instance.getName() + " cleared.");
        }
        instance = null;
    }
}