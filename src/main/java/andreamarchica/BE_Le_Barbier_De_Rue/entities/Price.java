package andreamarchica.BE_Le_Barbier_De_Rue.entities;

import java.util.HashMap;
import java.util.Map;

public class Price<T> {
    private final Map<HaircutType, Double> haircutPrices;
    private final Map<BeardcutType, Double> beardcutPrices;
    private static final double DEFAULT_HAIRCUT_PRICE = 0.0;
    private static final double DEFAULT_BEARDCUT_PRICE = 0.0;

    public Price() {
        haircutPrices = new HashMap<>();
        beardcutPrices = new HashMap<>();

        haircutPrices.put(HaircutType.TAGLIO_OVER_65, 16.0);
        haircutPrices.put(HaircutType.TAGLIO_BIMBO, 10.0);
        haircutPrices.put(HaircutType.TAGLIO_DI_CAPELLI_UOMO_CON_SHAMPOO, 18.0);
        haircutPrices.put(HaircutType.RASATURA_LATI, 10.0);
        haircutPrices.put(HaircutType.TAGLIO_TEEN, 15.0);

        beardcutPrices.put(BeardcutType.BARBA_TOSATORE_E_RASOIO, 8.0);
        beardcutPrices.put(BeardcutType.RIFINITURA_BARBA, 4.0);
        beardcutPrices.put(BeardcutType.RASATURA_COMPLETA_BARBA, 15.0);
        beardcutPrices.put(BeardcutType.BARBA_SOLO_TOSATORE, 6.0);
        beardcutPrices.put(BeardcutType.BARBA_A_FORBICE, 12.0);

    }

    public double getPriceForType(T cuttingType) {
        if (cuttingType instanceof HaircutType) {
            return haircutPrices.getOrDefault((HaircutType) cuttingType, DEFAULT_HAIRCUT_PRICE);
        } else if (cuttingType instanceof BeardcutType) {
            return beardcutPrices.getOrDefault((BeardcutType) cuttingType, DEFAULT_BEARDCUT_PRICE);
        }
        return 0.0;
    }
}