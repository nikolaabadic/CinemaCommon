package rs.ac.bg.fon.CinemaCommon.communication;

import java.io.Serializable;

public enum Operation implements Serializable {
    LOGIN,
    ADD_FILM,
    GET_FILMS,
    GET_HALLS,
    GET_TERMS,
    ADD_TERM,
    DELETE_FILM,
    GET_USERS,
    ADD_RESERVATION,
    GET_RESERVATIONS,
    DELETE_TERM,
    EDIT_TERM,
    DELETE_RESERVATION,
    GET_FILMS_WHERE,
    GET_TERMS_BY_FILM_ID,
    GET_TERMS_BY_HALL,
    GET_TERMS_BY_DATE,
    GET_RESERVATIONS_BY_TERM_ID,
    GET_RESERVATIONS_BY_USERNAME,
    GET_RESERVATIONS_BY_DATE,
    GET_FILM_BY_ID,
    GET_TERM_BY_ID,
    GET_RESERVATION_BY_ID
}
