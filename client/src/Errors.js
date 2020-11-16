export default function Errors ( {errors} ) {
    return (
        <>
            {errors.length > 0 && (
                <div className="alert alert-danger">
                    <p>The following errors occured</p>
                    {errors.map((error) => (
                        <p key={error}>{error}</p>
                    ))}
                </div>
            )}
        </>
    );
}