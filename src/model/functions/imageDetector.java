package model.functions;

public class imageDetector {
    private String photoID;

    public imageDetector() {
    }

    public boolean isPicture(String string) {
        StringBuilder fileType = new StringBuilder();
        int periodIndex = 0;

        for (int x = 0; x < string.length(); x++) {
            if ((string.charAt(x)) == '.') {
                periodIndex = x;
            }
        }

        if (periodIndex == 0) {
            return false;
        }

        for (int x = periodIndex + 1; x < string.length(); x++) {
            fileType.append(string.charAt(x));
        }

        switch (fileType.toString()) {
            case "png":
                return true;
            case "PNG":
                return true;
            case "jpg":
                return true;
            case "JPG":
                return true;
            case "jpeg":
                return true;
            case "JPEG":
                return true;
            case "gif":
                return true;
            case "GIF":
                return true;
        }

        return false;
    }

}
