# Known Issues

## Non-standard (driver specific) JDBC methods are not directly accessible

Many drivers provide methods that expose driver-specific, non-standard functionality. Most developers do not use
these features, but in the event that an application does use these features they are not natively supported by
P6Spy. For example, the MySQL JDBC drivers allow you to call the auto-increment function as follows:

    ((com.mysql.jdbc.PreparedStatement)stmt).getLastInsertId();

This fails when P6Spy is active since the prepared statement is actually a proxy generated by P6Spy. Since
the proxy is not a subclass of com.mysql.jdbc.PreparedStatement, the cast fails.  The only workaround available
requires code changes to the application.

All proxies generated by P6Spy implement the [java.sql.Wrapper](http://docs.oracle.com/javase/6/docs/api/java/sql/Wrapper.html)
interface.  This interface is part of the JDBC 4.0 API (Java 6 and later).  This provides a standard way to unwrap
the proxied object to obtain access to driver specific methods.

    // assuming MySQL JDBC driver
    PreparedStatement stmt = connection.prepareStatement("....");
    if( stmt.isWrapperFor(com.mysql.jdbc.PreparedStatement.class) {
      com.mysql.jdbc.PreparedStatement mySqlStmt = stmt.unwrap(com.mysql.jdbc.PreparedStatement.class);
      mySqlStmt.getLastInsertId();
    }

Please be aware that any P6Spy will not be able to log any actions performed against the unwrapped object.  This is
perfectly fine as long as you are only using the non-standard functionality.  However, if you use the unwrapped
PreparedStatement in the example above to execute a SQL statement, it would not be logged.

## OUT parameters of a stored procedure are not logged

The reason is that log message is written once the statement is executed. However the OUT parameters are not accessed until after the statement is executed.