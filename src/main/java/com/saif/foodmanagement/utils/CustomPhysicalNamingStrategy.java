package com.saif.foodmanagement.utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static java.util.Objects.nonNull;

/**
 * @author saifuzzaman
 */
public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

    private static final String REGEX = "([a-z])([A-Z])";
    private static final String REPLACEMENT = "$1_$2";

    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(identifier);
    }

    private Identifier convertToSnakeCase(Identifier identifier) {
        if (nonNull(identifier)) {
            String newName = identifier.getText()
                    .replaceAll(REGEX, REPLACEMENT)
                    .toLowerCase();

            return Identifier.toIdentifier(newName);
        } else {
            return null;
        }
    }
}

