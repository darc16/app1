 --------------------------------------------------------------
        -- Filename:  V1.3__tables.sql
        --------------------------------------------------------------

        -- Create the reports table
        CREATE TABLE reports_aud
        (
          rev                 INTEGER,
          rev_type            INTEGER,
          id                  INTEGER,
          version             INTEGER,
          description         TEXT,
          display_name        VARCHAR(255),
          reviewed            BOOLEAN,
          reference_source    INTEGER,
          priority            INTEGER,
          created_date        TIMESTAMP,
          last_modified_date  TIMESTAMP,
          is_custom_report    BOOLEAN DEFAULT FALSE,
          reserved            BOOLEAN,
          reserved_by         VARCHAR(255)
        );
