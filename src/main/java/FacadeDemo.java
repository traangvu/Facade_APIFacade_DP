public class FacadeDemo {

    public static void main(String[] args) {
        ApiFacade apiFacade = new ApiFacade();

        // 1. Get Chuck Norris joke
        try {
            String joke = apiFacade.getAttributeValueFromJson("https://api.chucknorris.io/jokes/random", "value");
            System.out.println("Random Chuck Norris Joke: " + joke);
        } catch (Exception e) {
            System.err.println("Failed to get joke: " + e.getMessage());
        }

        // 2. Get exchange rate for EUR to USD
        try {
            String usdRate = apiFacade.getAttributeValueFromJson("https://open.er-api.com/v6/latest/EUR", "rates.USD");
            System.out.println("EUR to USD exchange rate: " + usdRate);
        } catch (Exception e) {
            System.err.println("Failed to get exchange rate: " + e.getMessage());
        }
    }
}
