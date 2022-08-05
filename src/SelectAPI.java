public enum SelectAPI {
  NASA("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14"),
  IMDB("https://imdb-api.com/en/API/Top250Movies/k_a3xp2vhu");

  private final String value;

  SelectAPI(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
