package ImperatorToCK2.CK2;

import java.util.Optional;
import ImperatorToCK2.Importer;
import java.io.IOException;

public class LandedTitle {
    private final String name;
    private final Optional<String> color;
    private final Government government;
    private final Optional<Integer> capital;

    public LandedTitle(String tag, Optional<String> imperatorColor, String imperatorGovernment,
            Optional<Integer> imperatorCapital, Rank rank) throws IOException {
        this.name = rank.getLetter() + "_" + tag;

        this.color = imperatorColor;

        if (imperatorGovernment.equals("republic")) {
            this.government = Government.REPUBLIC;
        } else if (imperatorGovernment.equals("imperium") && rank.equals(Rank.EMPIRE)) {
            this.government = Government.EMPIRE;
        } else {
            this.government = Government.MONARCHY;
        }

        this.capital = convertCapital(imperatorCapital);
    }

    public LandedTitle(String tag) {
        this.name = Rank.BARONY.getLetter() + "_" + tag;
        this.color = Optional.empty();
        this.government = Government.PALACE;
        this.capital = Optional.empty();
    }

    public String getName() {
        return name;
    }

    public Optional<String> getColor() {
        return color;
    }

    public Government getGovernment() {
        return government;
    }

    public Optional<Integer> getCapital() {
        return capital;
    }

    private Optional<Integer> convertCapital(Optional<Integer> imperatorCapital) throws IOException {
        if (imperatorCapital.isPresent()) {
            Integer capital = Integer
                    .parseInt(Importer.importConvList("provinceConversion.txt", imperatorCapital.get())[1]);
            return Optional.of(capital);
        } else {
            return Optional.empty();
        }
    }
}
