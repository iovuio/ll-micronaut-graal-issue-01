package hello.world;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.AttributesBuilder;
import org.asciidoctor.Options;
import org.asciidoctor.OptionsBuilder;

import static org.asciidoctor.Asciidoctor.Factory.create;

@Controller("/hello")
public class HelloController {
    private Options options = OptionsBuilder.options()
            .attributes(AttributesBuilder.attributes().showTitle(true)).get();

    @Get(produces = MediaType.TEXT_PLAIN)
    public String index() {
        return "Hello World3";
    }

    @Post("/convert")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.TEXT_PLAIN)
    public String adocToHtml(@Body String adocContent) {
        Asciidoctor asciidoctor = create();
        return asciidoctor.convert(adocContent, options);
    }
}
