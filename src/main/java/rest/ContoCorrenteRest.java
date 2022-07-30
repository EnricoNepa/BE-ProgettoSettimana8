package rest;

import java.util.ArrayList;
import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



import entity.ContoCorrente;
import entity.Operazione;


@Path("/conto")
public class ContoCorrenteRest {
	private static List<ContoCorrente> conti=new ArrayList<>();
	private static List<Operazione> operazioni=new ArrayList<>();



	@GET
	@Path("/listaconti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContoCorrente> getAllConti() {

		return conti;
	}


	@POST
	@Path("/crea")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crea(ContoCorrente c) {
		conti.add(c);
		String result="Inserimento avvenuto con successo";
		return Response.status(201).entity(result).build();

	}

	@PUT
	@Path("/aggiorna")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response aggiorna(ContoCorrente c) {

		if(conti.contains(c)) {
			int index=conti.lastIndexOf(c);
			conti.set(index, c);
			return Response.status(200).entity("Aggiornamento effettuata con successo").build();

		}
		return Response.status(404).entity("Errore aggiornamento").build();
	}

	@DELETE
	@Path("/cancella")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancella(ContoCorrente c) {

		if(conti.contains(c)) {
			conti.remove(c);
			return Response.status(200).entity("Cancellazione effettuata con successo").build();
		}
		return Response.status(404).entity("Errore cancellazione").build();
	}

	@PUT
	@Path("/operazione")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response operazione(Operazione o) {

		for (ContoCorrente conto : conti) {

			if (conto.getIban().equals(o.getIban())) {

				//prelievo
				if(o.isPrelievo()==true&o.getImporto()>0) {
					double saldoFinale=conto.getSaldo() - o.getImporto();
					conto.setSaldo(saldoFinale);
					int index=conti.lastIndexOf(conto);
					conti.set(index, conto);
					operazioni.add(o);
					return Response.status(200).entity("Prelievo effettuato").build();
				}
				//deposito
				if(o.isDeposito()==true&o.getImporto()>0) {
					double saldoFinale=conto.getSaldo() + o.getImporto();
					conto.setSaldo(saldoFinale);
					int index=conti.lastIndexOf(conto);
					conti.set(index, conto);
					operazioni.add(o);
					return Response.status(200).entity("Deposito effettuato").build();
				}
			}
		}
		return Response.status(404).entity("Errore operazione:iban errato o importo non valido").build();
	}
	@GET
	@Path("/movimenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Operazione> getAllMovimenti() {

		return operazioni;
	}
}