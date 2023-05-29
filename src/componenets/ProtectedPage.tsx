import React from "react";

interface IprotectedPageProps {
  children: React.ReactNode;
}

export default function ProtectedPage({ children }: IprotectedPageProps) {
  return <>{children}</>;
}
