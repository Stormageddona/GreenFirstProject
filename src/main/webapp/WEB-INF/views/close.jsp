<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <script>
            if (window.opener) {
                let data = '${userdata}';
                window.opener.postMessage(data, window.location.origin);
                this.close()
            }
    </script>
</html>