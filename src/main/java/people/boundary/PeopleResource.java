package people.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

	@GET
	@Path("{id}")
	public Response getPeopleById(@PathParam("id") Integer peopleId) {
		return Response //
				.status(200) //
				.entity(em.find(People.class, peopleId)) //
				.build();
	}

	@POST
	public Response addNewPeople(People people) {
		em.persist(people);
		em.flush();
		return Response //
				.status(200) //
				.entity(people) //
				.build();
	}

	@PUT
	public Response updateExistingPeople(People peopleToUpdate) {
		return Response //
				.status(200) //
				.entity(em.merge(peopleToUpdate)) //
				.build();
	}

	@DELETE
	@Path("/delete/{id}")
	public Response deletePeopleById(@PathParam("id") Integer peopleId) {
		People peopleToDelete = em.find(People.class, peopleId);
		em.remove(peopleToDelete);
		return Response //
				.status(200) //
				.entity(peopleToDelete) //
				.build();
	}

}
