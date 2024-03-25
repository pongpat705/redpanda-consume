package th.co.prior.training.consumer.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import th.co.prior.training.model.ReportModel;

@Slf4j
@Component
public class KafkaConsumeComponent {
    private ObjectMapper mapper;

    public KafkaConsumeComponent(@Qualifier("mapper") ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @KafkaListener(topics = "${app.config.kafka.topic}", groupId = "${app.config.kafka.group}")
    public void consumeMessage(@Payload String message) {
        log.info("factory got message got {}", message);

        try {
            ReportModel reportModel = mapper.readValue(message, ReportModel.class);
            System.out.println(reportModel.toString());
            Thread.sleep(2000);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
