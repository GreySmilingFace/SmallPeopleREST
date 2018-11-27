package people.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import people.entity.People;

@Stateless
@Path("/peoples")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

	@PersistenceContext
	private EntityManager em;

	@POST
	public Response addNewPeople(People people) {
		em.persist(people);
		em.flush();
		return Response.status(200).entity(people).build();
	}

}
