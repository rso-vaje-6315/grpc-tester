package si.rso.grpc.services;

import com.kumuluz.ee.grpc.client.GrpcChannelConfig;
import com.kumuluz.ee.grpc.client.GrpcChannels;
import com.kumuluz.ee.grpc.client.GrpcClient;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import grpc.Invoice;
import grpc.InvoiceServiceGrpc;
import io.grpc.stub.StreamObserver;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.net.ssl.SSLException;

@ApplicationScoped
public class InvoiceService {
    
    private Logger LOG = LogManager.getLogger(InvoiceService.class.getSimpleName());

    private InvoiceServiceGrpc.InvoiceServiceStub invoiceServiceStub;
    
    @PostConstruct
    private void init() {
        try {
            GrpcChannels clientPool = GrpcChannels.getInstance();
            GrpcChannelConfig channelConfig = clientPool.getGrpcClientConfig("invoice-client");
            GrpcClient client = new GrpcClient(channelConfig);
            
            invoiceServiceStub = InvoiceServiceGrpc.newStub(client.getChannel());
            
        } catch (SSLException e) {
            e.printStackTrace();
        }
    }
    
    public void createInvoice() {
    
        var request = Invoice.InvoiceRequest.newBuilder()
            .setOrderId("OrderID")
            .build();
        
        LOG.info("Starting GRPC connection");
        
        invoiceServiceStub.createInvoice(request, new StreamObserver<Invoice.InvoiceResponse>() {
            @Override
            public void onNext(Invoice.InvoiceResponse invoiceResponse) {
                LOG.info("Connection succeeded! Returned status: " + invoiceResponse.getStatus());
                LOG.info("Generated invoice id: " + invoiceResponse.getInvoiceId());
            }
    
            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
    
            @Override
            public void onCompleted() {
                LOG.info("GRPC connection completed!");
            }
        });
    }

}
