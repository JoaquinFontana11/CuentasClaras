package CuentasClaras.CuentasClaras.Request;


public record AddMemberDTO(
		boolean isGroup, 
		String inviteName, 
		int userId) {

}
