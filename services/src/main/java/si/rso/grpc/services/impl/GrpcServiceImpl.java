package si.rso.grpc.services.impl;

import si.rso.grpc.services.GrpcService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GrpcServiceImpl implements GrpcService {
    @Override
    public void sendGrpcMessage(String message) {
    
    }
}
