package igor.henrique.screenMatchAPI.enums;

public enum Category {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    AVENTURA("Adventure", "Aventura"),
    FICCAO("Fiction", "Ficção"),
    SUSPENSE("Suspense", "Suspense"),
    POLICIAL("Policial", "Policial");

    private String categoryOmdb;
    private String categoryPortugues;

    Category(String categoryOmdb, String categoryPortugues){
        this.categoryOmdb = categoryOmdb;
        this.categoryPortugues = categoryPortugues;
    }

    public static Category fromString(String text) {
        for (Category category : Category.values()) {
            if (category.categoryOmdb.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Category fromPortugues(String text) {
        for (Category category : Category.values()) {
            if (category.categoryPortugues.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
    }

