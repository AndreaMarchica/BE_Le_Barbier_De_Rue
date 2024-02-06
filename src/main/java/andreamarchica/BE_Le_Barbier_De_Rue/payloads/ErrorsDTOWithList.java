package andreamarchica.BE_Le_Barbier_De_Rue.payloads;

import java.util.Date;
import java.util.List;

public record ErrorsDTOWithList(
        String message,
        Date timestamp,
        List<String> errorsList
) {
}