package fr.webforce.controllers;

import fr.webforce.entities.BookEntity;
import fr.webforce.entities.ErrorEntity;
import fr.webforce.services.BookService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;


@Path("/books")
public class BookController {

	BookService bookService = new BookService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		return Response.ok(bookService.findAll()).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(BookEntity bookEntity) {
		Optional<Integer> nb = bookService.insert(bookEntity);
		if (nb.isPresent()) {
			Optional<BookEntity> b = bookService.findByID(bookEntity.isbn());
			return Response.ok(b.orElse(null)).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorEntity("Not Insert To DB", 500)).build();
	}

	@GET
	@Path("{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByID(@PathParam("isbn") String isbn) {
		Optional<BookEntity> b = bookService.findByID(isbn);
		if (b.isPresent()) {
			return Response.ok(b.get()).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity(new ErrorEntity("Not Found Book", 404)).build();
	}

	@DELETE
	@Path("{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteByID(@PathParam("isbn") String isbn) {
		Optional<Integer> nb = bookService.delete(isbn);
		if (nb.isPresent()) {
			if (nb.get() == -1) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorEntity("Error request SQL", 500)).build();
			}else{
				return Response.ok(new ErrorEntity("Delete " + isbn + " is OK.", 200)).build();
			}
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorEntity("Not Delete to Database", 500)).build();
	}
}
