package spn.services.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class spnAlimRequestNipTask implements Task {

    private final String endpoint = "/SPN/services/SPNWebService";
    private final String cellPhone;

    public spnAlimRequestNipTask(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String soapEnvelope =
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:spn=\"http://SPNWebService.server.ws.spnv.spn.tid.es/\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <spn:spn_alim_requestNip>\n" +
                        "         <generateNipParam>\n" +
                        "            <msisdn>"+ cellPhone +"</msisdn>\n" +
                        "            <receiverOperator>00004</receiverOperator>\n" +
                        "            <technology>MOV</technology>\n" +
                        "         </generateNipParam>\n" +
                        "         <callerEntity>\n" +
                        "            <externalId>Fullstack</externalId>\n" +
                        "            <systemId>2</systemId>\n" +
                        "            <userLogin>605807</userLogin>\n" +
                        "         </callerEntity>\n" +
                        "      </spn:spn_alim_requestNip>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>";

actor.attemptsTo(
        Post.to(endpoint)
                .with(request -> request
                        .header("Content-Type", "text/xml; charset=utf-8")
                        .header("SOAPAction", "http://webservice.spm.spm.alim.com/spn_alim_requestNip")
                        .body(soapEnvelope)

                )
);

    }
    public static spnAlimRequestNipTask whithcellphone( String cellPhone){
        return instrumented(spnAlimRequestNipTask.class, cellPhone);
    }
}
