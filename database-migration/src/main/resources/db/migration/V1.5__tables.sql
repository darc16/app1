 --------------------------------------------------------------
        -- Filename:  V1.5__tables.sql
        --------------------------------------------------------------

        -- Create the indicator types table
        CREATE TABLE indicator_types
        (

          id                  INTEGER,
          name                VARCHAR(255) NOT NULL
        );


        -- Create the indicator link_reports_indicators table
        CREATE TABLE link_reports_indicators
        (

          id                  INTEGER,
          report              INTEGER NOT NULL,
          indicator           INTEGER NOT NULL
        );

Alter table indicators add column ind_type integer null;