package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer implements Observer {

	private String	email;
	private String	firstName;
	private String	lastName;

	public Customer(String email, String firstName, String lastName) {
		setEmail(email);
		setFirstName(firstName);
		setLastName(lastName);
	}

	public Customer() {}

	public void setEmail(String email) {
		if (email.isEmpty()) throw new IllegalArgumentException("No email given");
		String USERID_PATTERN =
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if ( !m.matches()) throw new IllegalArgumentException("Email not valid");
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName.isEmpty()) throw new IllegalArgumentException("No firstname given");
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName.isEmpty()) throw new IllegalArgumentException("No last name given");
		this.lastName = lastName;
	}

	@Override
	public void update(String message) {
		System.out.println("Mail to " + email + ": " + message);
	}
	
	@Override
	public String toString() {
		return "Customer(" + firstName + " " + lastName + "," + email + ")";
	}
}
