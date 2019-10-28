package service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.UsuarioDAO;

//@Path("login")
public class UsuarioService {

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean login(@PathParam("email") String email, @PathParam("senha") String senha) {
		// System.out.println("Entrou em /login no usuarioSerivce");
		return UsuarioDAO.eLoginValido(email, senha);

	}
//Implementar o restante
//	
//	@GET
//	@Path("{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public BemDeConsumo getProduto(@PathParam("id") String id) {
//		BemDeConsumo produto = Estoque.bemDeConsumoDao.get(id);
//		return produto;
//	}
//
//	@POST
//	@Path("add")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addProduct(BemDeConsumo produto) {
//		Estoque.bemDeConsumoDao.add(produto);
//		return Response.status(Status.CREATED).build();
//	}
//
//	@PUT
//	@Path("update")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response updateProduct(BemDeConsumo produto) {
//		Estoque.bemDeConsumoDao.update(produto);
//		return Response.ok().build();
//	}
//
//	@DELETE
//	@Path("delete/{id}")
//	public Response deleteProduct(@PathParam("id") String id) {
//		BemDeConsumo produto = Estoque.bemDeConsumoDao.get(id);
//		Estoque.bemDeConsumoDao.remove(produto);
//		return Response.status(202).entity("Produto " + id + " removido com sucesso.").build();
//	}
}
