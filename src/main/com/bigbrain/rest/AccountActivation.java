package com.bigbrain.rest;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.bigbrain.services.UserService;

@Path("/account")
public class AccountActivation {
	
	@GET
	@Path("/activation/{email}/{codeActivation}")
	public Response getMsg(@PathParam("email") String email, @PathParam("codeActivation") String codeActivation) {
		System.out.println("Email:"+email);
		System.out.println("codeActivation:"+codeActivation);
		UserService userService = new UserService();
		try {
		userService.activateUser(email, codeActivation);
		}catch(Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		return Response.status(200).entity("User activated").build();
	}
}
