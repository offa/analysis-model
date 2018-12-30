package edu.hm.hafner.analysis;

import java.util.Optional;
import java.util.regex.Matcher;

import edu.hm.hafner.util.LookaheadStream;

/**
 * Parses a report file line by line for issues using a pre-defined regular expression. If the regular expression
 * matches then the abstract method {@link #createIssue(Matcher, IssueBuilder)} will be called. Sub classes need to
 * provide an implementation that transforms the {@link Matcher} instance into a new issue. This class basically
 * simplifies the parent class {@link LookaheadParser} so that sub classes are not allowed to consume additional lines
 * of the report anymore.
 *
 * @author Ullrich Hafner
 */
public abstract class RegexpLineParser extends LookaheadParser {
    private static final long serialVersionUID = 434000822024807289L;

    /**
     * Creates a new instance of {@link RegexpLineParser}.
     *
     * @param pattern
     *         pattern of compiler warnings.
     */
    protected RegexpLineParser(final String pattern) {
        super(pattern);
    }

    @Override
    protected final Optional<Issue> createIssue(final Matcher matcher, final LookaheadStream lookahead,
            final IssueBuilder builder) throws ParsingException {
        return createIssue(matcher, builder);
    }

    /**
     * Creates a new issue for the specified pattern. This method is called for each matching line in the specified
     * file. If a match is a false positive, then return {@link Optional#empty()} to ignore this warning.
     *
     * @param matcher
     *         the regular expression matcher
     * @param builder
     *         the issue builder to use
     *
     * @return a new annotation for the specified pattern
     * @throws ParsingException
     *         Signals that during parsing a non recoverable error has been occurred
     */
    protected abstract Optional<Issue> createIssue(Matcher matcher, IssueBuilder builder);
}
