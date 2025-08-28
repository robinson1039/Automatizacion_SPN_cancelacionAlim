package spn.services.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class spn_alim_requestPortabilityTask implements Task {
    private final String endpoint = "/SPN/services/SPNWebService";
    private final String cellPhone;
    private final String nip;
    private final String date;

    public spn_alim_requestPortabilityTask(String cellPhone, String nip, String date) {
        this.cellPhone = cellPhone;
        this.nip = nip;
        this.date = date;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
    String soapEnvelope =
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:spn=\"http://SPNWebService.server.ws.spnv.spn.tid.es/\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <spn:spn_alim_requestPortability>\n" +
                    "         <newPortabilityParam>\n" +
                    "            <comments/>\n" +
                    "            <donorOperator>00033</donorOperator>\n" +
                    "            <modalityDonor>POS</modalityDonor>\n" +
                    "            <msisdnCorrelationList>\n" +
                    "               <modalityReceiver>HYB</modalityReceiver>\n" +
                    "               <msisdn>"+cellPhone+"</msisdn>\n" +
                    "               <nip>"+nip+"</nip>\n" +
                    "               <nrn>143</nrn>\n" +
                    "            </msisdnCorrelationList>\n" +
                    "            <personalDataType>\n" +
                    "               <addressData>\n" +
                    "                  <city>VALLEDUPAR</city>\n" +
                    "                  <delegation>COSTA</delegation>\n" +
                    "                  <neighborhood>VALLEDUPAR-VALLEDUPAR</neighborhood>\n" +
                    "                  <state>CESAR</state>\n" +
                    "                  <street>0</street>\n" +
                    "               </addressData>\n" +
                    "               <identificationNumber>42490705</identificationNumber>\n" +
                    "               <identificationType>CC</identificationType>\n" +
                    "               <name1>MARLENE</name1>\n" +
                    "               <nationality/>\n" +
                    "               <personalId>1018611046854518</personalId>\n" +
                    "               <personalType>1</personalType>\n" +
                    "               <surname1>VARGAS</surname1>\n" +
                    "               <surname2>CORPAS</surname2>\n" +
                    "               <expeditionDate>19760117</expeditionDate>\n" +
                    "            </personalDataType>\n" +
                    "            <receiverOperator>00004</receiverOperator>\n" +
                    "            <suggestedFVC>"+date+"</suggestedFVC>\n" +
                    "            <technology>MOV</technology>\n" +
                    "         </newPortabilityParam>\n" +
                    "         <callerEntity>\n" +
                    "            <email>info-telefonica@grupocef.com</email>\n" +
                    "            <externalId>Fullstack</externalId>\n" +
                    "            <office/>\n" +
                    "            <systemId>2</systemId>\n" +
                    "            <userLogin>423676</userLogin>\n" +
                    "         </callerEntity>\n" +
                    "      </spn:spn_alim_requestPortability>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
    actor.attemptsTo(
            Post.to(endpoint)
                    .with(request -> request
                            .header("Content-Type", "text/xml; charset=utf-8")
                            .header("SOAPAction", "http://webservice.spm.spm.alim.com/spn_alim_requestPortability")
                            .body(soapEnvelope)
                    )
    );
    }
    public static spn_alim_requestPortabilityTask withUserData( String cellPhone, String nip, String date){
        return instrumented(spn_alim_requestPortabilityTask.class, cellPhone, nip, date);
    }
}
