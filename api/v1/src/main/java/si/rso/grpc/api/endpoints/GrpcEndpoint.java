package si.rso.grpc.api.endpoints;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import si.rso.grpc.services.GrpcService;

@Path("/grpc")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GrpcEndpoint {
    
    @Inject
    private GrpcService grpcService;
    
    @POST
    public Response sendGrpcMessage() {
        grpcService.sendGrpcMessage("");
        return Response.ok().build();
    }

}
