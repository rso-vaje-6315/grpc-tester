package si.rso.grpc.api.endpoints;

import si.rso.grpc.services.InvoiceService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/grpc")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GrpcEndpoint {
    
    @Inject
    private InvoiceService invoiceService;
    
    @POST
    @Path("/create-invoice")
    public Response sendGrpcMessage() {
        invoiceService.createInvoice();
        return Response.ok().build();
    }

}
