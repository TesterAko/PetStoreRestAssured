package PetStore;


public class JSONHandler {

    static String getPetRequestBody(int id, String name, String status) {

        return String.format("""
                {
                    "id": %d,
                    "category": {
                        "id": 0,
                        "name": "string"
                    },
                    "name": "%s",
                    "photoUrls": [
                        "http://placeimg.com/640/480"
                    ],
                    "tags": [
                        {
                            "id": 0,
                            "name": "string"
                        }
                    ],
                    "status": "%s"
                }
                """, id, name, status);
    }
}
