package com.kma.library.models;

import java.util.List;

public record Response(Integer count, String next, String previous, List<Book> results) {
}
