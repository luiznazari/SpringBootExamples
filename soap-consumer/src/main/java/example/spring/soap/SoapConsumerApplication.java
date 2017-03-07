
package example.spring.soap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.style.ToStringCreator;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;

@SpringBootApplication
public class SoapConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapConsumerApplication.class);
    }

    @Bean
    public CommandLineRunner lookup(CountryClient quoteClient) {
        return args -> {
            String name = "Spain";

            if (args.length > 0) {
                name = args[0];
            }
            GetCountryResponse response = quoteClient.getQuote(name);

            System.err.println("RESPONSE: " + countryToString(response.getCountry()));
        };
    }

    private static String countryToString(Country that) {
        return new ToStringCreator(Country.class).append(that.getName()).append(that.getCapital()).append(that.getPopulation())
                .append("").append(that.getCurrency()).toString();
    }

}
