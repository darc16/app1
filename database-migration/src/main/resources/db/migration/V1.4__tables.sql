 --------------------------------------------------------------
        -- Filename:  V1.3__tables.sql
        --------------------------------------------------------------

        -- Create the reports table
        CREATE TABLE indicators
        (

          id                  INTEGER,
          value        VARCHAR(100),
          created_date        TIMESTAMP,
          created_by         VARCHAR(100)
        );
