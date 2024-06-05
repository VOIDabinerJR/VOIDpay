package tech.buildrun.VOIDpay.service;

import java.util.Map;
import com.fc.sdk.APIContext;
import com.fc.sdk.APIRequest;
import com.fc.sdk.APIResponse;
import com.fc.sdk.APIMethodType;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationPovisoryDepositMpesa {



    public boolean tryAuthorizeMpesa(   String originBankAccount, String transitionReference, String value, String thirdPartyReference,String serviceProviderCode) {
        //variaveis inicializacao padrao

        originBankAccount ="258865218679";
        transitionReference=  "T12344C";
        value = "1003";
        thirdPartyReference = "113PA2D";
        serviceProviderCode= "171717";
        APIContext context = getApiContext();
        boolean returnAuthorization = false;

        // Add Header
        context.addHeader("Origin", "*");

        // Set parameters used for the API
        context.addParameter("input_TransactionReference",transitionReference);
        context.addParameter("input_CustomerMSISDN",originBankAccount );
        context.addParameter("input_Amount", value);
        context.addParameter("input_ThirdPartyReference", thirdPartyReference);
        context.addParameter("input_ServiceProviderCode",serviceProviderCode );


        // Create API request and execute it.
        APIRequest request = new APIRequest(context);
        APIResponse response = request.execute();

        // Print results to the console
        if (response != null) {
            System.out.println(response.getStatusCode() + " - " + response.getReason());
            System.out.println(response.getResult());


            if (response.getParameter("output_ResponseDesc").equals("Duplicate Transaction")){
                returnAuthorization = true;

// da para retornar o response.getParameter depois tratar o parameter com exceptions ou sucesso

            }

            for (Map.Entry<String, String> entry : response.getParameters().entrySet()) {
                System.out.println(entry.getKey() + ":" + response.getParameter(entry.getKey()));
            }
        }
        return returnAuthorization;
    }

    private static APIContext getApiContext() {
        String apiKey = "6ochu3zcdsz50n1618lckkcdpdmd5zmw";
        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAmptSWqV7cGUUJJhUBxsMLonux24u+FoTlrb+4Kgc6092JIszmI1QUoMohaDDXSVueXx6IXwYGsjjWY32HGXj1iQhkALXfObJ4DqXn5h6E8y5/xQYNAyd5bpN5Z8r892B6toGzZQVB7qtebH4apDjmvTi5FGZVjVYxalyyQkj4uQbbRQjgCkubSi45Xl4CGtLqZztsKssWz3mcKncgTnq3DHGYYEYiKq0xIj100LGbnvNz20Sgqmw/cH+Bua4GJsWYLEqf/h/yiMgiBbxFxsnwZl0im5vXDlwKPw+QnO2fscDhxZFAwV06bgG0oEoWm9FnjMsfvwm0rUNYFlZ+TOtCEhmhtFp+Tsx9jPCuOd5h2emGdSKD8A6jtwhNa7oQ8RtLEEqwAn44orENa1ibOkxMiiiFpmmJkwgZPOG/zMCjXIrrhDWTDUOZaPx/lEQoInJoE2i43VN/HTGCCw8dKQAwg0jsEXau5ixD0GUothqvuX3B9taoeoFAIvUPEq35YulprMM7ThdKodSHvhnwKG82dCsodRwY428kg2xM/UjiTENog4B6zzZfPhMxFlOSFX4MnrqkAS+8Jamhy1GgoHkEMrsT5+/ofjCx0HjKbT5NuA2V/lmzgJLl3jIERadLzuTYnKGWxVJcGLkWXlEPYLbiaKzbJb2sYxt+Kt5OxQqC1MCAwEAAQ==";

        // Create API Context
        APIContext context = new APIContext();
        // Set API key that can be found in the user profile section
        context.setApiKey(apiKey);
        // Set Public key that can be found in the user profile section
        context.setPublicKey(publicKey);
        // Set SSL true or false
        context.setSsl(true);
        // Set the method type of the HTTP Request (GET, POST, PUT)
        context.setMethodType(APIMethodType.POST);
        // Set the address of the API Server
        context.setAddress("api.sandbox.vm.co.mz");
        // Set the TCP port of the API Server
        context.setPort(18352);
        // Set path for the API
        context.setPath("/ipg/v1x/c2bPayment/singleStage/");
        return context;
    }
}
